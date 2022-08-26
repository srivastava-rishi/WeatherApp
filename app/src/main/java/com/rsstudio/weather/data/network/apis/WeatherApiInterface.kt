package com.rsstudio.weather.data.network.apis

import com.rsstudio.weather.data.network.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiInterface {

    @GET("forecast?" +
            "timezone=IST&" +
            "hourly=temperature_2m," +
            "weathercode," +
            "relativehumidity_2m," +
            "windspeed_10m," +
            "pressure_msl&" +
            "daily=" +
            "temperature_2m_max&" +
            "latitude={latitude}&longitude={longitude}")
    suspend fun getWeatherData(
        @Path("latitude") latitude:Float,
        @Path("longitude") longitude:Float
    ): Response<Weather>

}