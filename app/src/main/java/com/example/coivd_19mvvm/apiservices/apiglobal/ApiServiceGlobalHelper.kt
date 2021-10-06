package com.example.coivd_19mvvm.apiservices.apiglobal

import com.example.coivd_19mvvm.data.WorldWideCases
import retrofit2.Response

interface ApiServiceGlobalHelper {

    suspend fun getGlobalData(): Response<WorldWideCases>
}