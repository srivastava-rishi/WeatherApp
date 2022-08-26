package com.rsstudio.weather.ui.main.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.rsstudio.weather.app.App
import com.rsstudio.weather.data.network.model.Weather
import com.rsstudio.weather.data.network.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository,
    private val app: App
) : ViewModel() {

    var logTag = "@MainViewModel"

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // the pattern that i usually follow
    private val _weatherData: MutableLiveData<Response<Weather>> = MutableLiveData()
    val weatherData: LiveData<Response<Weather>> get() = _weatherData

    private var _locationPointsData: MutableLiveData<MutableList<Double>> = MutableLiveData()
    val locationPointsData: MutableLiveData<MutableList<Double>> get() = _locationPointsData

    init {
        getLocationPoints()
    }


    @SuppressLint("MissingPermission")
    private fun getLocationPoints() {

        Log.d(logTag, "getLocationPoints: " + "line no 46")

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(app.applicationContext)

        var temp: MutableList<Double> = mutableListOf()

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->

            if (location!= null){
                temp.add(location.latitude)
                temp.add(location.longitude)
                _locationPointsData.value = temp
            }

        }

    }

    fun getWeatherData(latitude: Double, longitude: Double) {

        viewModelScope.launch {
            val result = repository.getWeatherData(latitude, longitude)
            _weatherData.value = result
        }
    }


}