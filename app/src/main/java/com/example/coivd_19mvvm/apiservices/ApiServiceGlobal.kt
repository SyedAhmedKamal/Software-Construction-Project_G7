package com.example.coivd_19mvvm.apiservices

import com.example.coivd_19mvvm.data.WorldWideCases
import com.example.coivd_19mvvm.util.Constants.GLOBAL_FEED
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceGlobal {

    @GET(GLOBAL_FEED)
    suspend fun getGlobalData(): Response<WorldWideCases>
}