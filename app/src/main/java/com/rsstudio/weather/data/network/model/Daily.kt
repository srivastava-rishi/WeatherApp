package com.rsstudio.weather.data.network.model

data class Daily(
    val temperature_2m_max: List<Double>,
    val time: List<String>
)