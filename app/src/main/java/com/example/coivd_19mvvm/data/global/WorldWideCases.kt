package com.example.coivd_19mvvm.data.global

data class WorldWideCases(
    val todayCases: Int,
    val critical: Int,
    val todayDeaths: Int,
    val population: Long,
    val todayRecovered: Int,
    val active: Long,
)