package com.example.coivd_19mvvm.apiservices.apicountries

import com.example.coivd_19mvvm.data.Countries
import com.example.coivd_19mvvm.util.Constants.COUNTRIES_FEED
import retrofit2.Response
import retrofit2.http.GET

interface ApiCountries {
    @GET(COUNTRIES_FEED)
    suspend fun getCountries(): Response<Countries>
}