package com.example.coivd_19mvvm.data.global

data class WorldWideCases(
    val cases: Int,
    val critical: Int,
    val deaths: Int,
    val population: Long,
    val recovered: Int,
    val tests: Long,
)