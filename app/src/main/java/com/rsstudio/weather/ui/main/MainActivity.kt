package com.rsstudio.weather.ui.main


import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsstudio.weather.R
import com.rsstudio.weather.data.network.model.Weather
import com.rsstudio.weather.databinding.ActivityMainBinding
import com.rsstudio.weather.ui.base.BaseActivity
import com.rsstudio.weather.ui.main.adapter.DailyForecastAdapter
import com.rsstudio.weather.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener  {

    lateinit var binding: ActivityMainBinding

    private lateinit var dailyForecastAdapter: DailyForecastAdapter

    private val viewModel: MainViewModel by viewModels()

    var logTag = "@MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        initAction()
        initRecyclerView()
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
                val list: MutableList<Weather> = mutableListOf()
//                Log.d(logTag, "initObservers: "  + it.body())
                list.add(it.body()!!)
                 Log.d(logTag, "list:" + list[0].daily)
                dailyForecastAdapter.submitList(list[0].daily)
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

    private fun initRecyclerView() {
        val llm = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvDailyForecast.setHasFixedSize(true)
        binding.rvDailyForecast.layoutManager = llm
        dailyForecastAdapter = DailyForecastAdapter(this)
        binding.rvDailyForecast.adapter = dailyForecastAdapter

    }

    override fun onClick(p0: View?) {

        when(p0?.id){

            R.id.ivSearch -> {
              search()
            }

        }
    }
}