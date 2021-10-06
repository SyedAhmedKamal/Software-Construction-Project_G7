package com.example.coivd_19mvvm.repository

import com.example.coivd_19mvvm.apiservices.apicountries.ApiCountriesHelper
import com.example.coivd_19mvvm.apiservices.apiglobal.ApiServiceGlobalHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiServiceGlobalHelper: ApiServiceGlobalHelper,
    private val apiCountriesHelper: ApiCountriesHelper
) {
    suspend fun getGlobalData() = apiServiceGlobalHelper.getGlobalData()
    suspend fun getCountriesData() = apiCountriesHelper.getCountries()
}