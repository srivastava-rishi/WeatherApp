package com.rsstudio.weather.ui.main


import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rsstudio.weather.R
import com.rsstudio.weather.databinding.ActivityMainBinding
import com.rsstudio.weather.ui.base.BaseActivity
import com.rsstudio.weather.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener  {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    var logTag = "@MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        initAction()
        initObservers()


    }

    private fun initAction() {
        binding.ivSearch.setOnClickListener(this)
    }

    private fun initObservers() {

        viewModel.locationPointsData.observe(this) {

            if (it != null) {
                Log.d(logTag, "initObservers: $it")
                viewModel.getWeatherData(it[0],it[1])
            }else{
                //
            }
        }

        viewModel.weatherData.observe(this) {

            if (it.isSuccessful) {
                Log.d(logTag, "initObservers: "  + it.body())
            } else {
                Log.d(logTag, "error: ${it.errorBody()} ")
            }


        }



    }

    private fun search() {

        var searchText = binding.searchInput.text

        if (searchText.isNotEmpty() || searchText != null) {

            try{
                val  gc = Geocoder(this)
                var addresses = gc.getFromLocationName(searchText.toString(),2)
                var address = addresses[0]

                Log.d(logTag, "searchText: $searchText")
                Log.d(logTag, "search: " + address.latitude)
                Log.d(logTag, "search: " + address.longitude)
                Log.d(logTag, "search: " + address.locality)
            }catch(e:Exception){
                Log.d(logTag, "search: " + e.message)
            }


        }
    }

    override fun onClick(p0: View?) {

        when(p0?.id){

            R.id.ivSearch -> {
              search()
            }

        }
    }
}