package com.example.coivd_19mvvm.apiservices.apicountries

import com.example.coivd_19mvvm.data.local.Countries
import retrofit2.Response
import javax.inject.Inject

class CountriesImple @Inject constructor(
    private val apiCountries: ApiCountries
) : ApiCountriesHelper {
    override suspend fun getCountries(): Response<Countries> = apiCountries.getCountries()
}