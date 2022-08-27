package com.rsstudio.weather.data.network.model

data class Weather(
    val daily: Daily,
    val elevation: Int,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val timezone: String,
)