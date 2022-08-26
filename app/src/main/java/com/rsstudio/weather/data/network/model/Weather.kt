package com.rsstudio.weather.data.network.model

data class Weather(
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Int,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Int,
    val longitude: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)