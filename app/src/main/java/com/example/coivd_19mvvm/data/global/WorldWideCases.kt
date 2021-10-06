package com.example.coivd_19mvvm.data.global

data class WorldWideCases(
    val active: Int,/**/
    val cases: Int,
    val critical: Int,
    val deaths: Int,
    val population: Long,
    val recovered: Int,
    val tests: Long,
    val todayCases: Int,/**/
    val todayDeaths: Int,/**/
    val todayRecovered: Int,/**/
    val updated: Long/**/
)