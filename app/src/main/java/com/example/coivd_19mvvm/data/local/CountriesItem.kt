package com.example.coivd_19mvvm.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountriesItem(
    val continent: String,
    val country: String,
    val countryInfo: CountryInfo,
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
    ) : Parcelable
}