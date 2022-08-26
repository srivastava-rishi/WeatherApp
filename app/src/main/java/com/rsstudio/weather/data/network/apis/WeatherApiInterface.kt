package com.rsstudio.weather.data.network.apis

import com.rsstudio.weather.data.network.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("forecast?timezone=IST&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&daily=temperature_2m_max,weathercode")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
    ): Response<Weather>

}