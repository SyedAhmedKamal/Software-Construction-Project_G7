package com.example.coivd_19mvvm.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountriesItem(
    val active: Int,
    val cases: Int,
    val continent: String,
    val country: String,
    val countryInfo: CountryInfo,
    val critical: Int,
    val deaths: Int,
    val population: Int,
    val recovered: Int,
    val tests: Int,
    val todayCases: Int,
    val todayDeaths: Int,
    val todayRecovered: Int,
    val updated: Long
) : Parcelable {
    @Parcelize
    data class CountryInfo(
        val _id: Int,
        val flag: String,
        val iso2: String,
        val iso3: String,
        val lat: Int,
        val long: Int
    ) : Parcelable
}