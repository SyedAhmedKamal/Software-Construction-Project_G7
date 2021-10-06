package com.example.coivd_19mvvm.apiservices.apicountries

import com.example.coivd_19mvvm.data.Countries
import retrofit2.Response

interface ApiCountriesHelper {
    suspend fun getCountries(): Response<Countries>
}