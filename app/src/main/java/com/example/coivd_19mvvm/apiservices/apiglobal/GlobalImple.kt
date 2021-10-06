package com.example.coivd_19mvvm.apiservices.apiglobal

import com.example.coivd_19mvvm.apiservices.apiglobal.ApiServiceGlobal
import com.example.coivd_19mvvm.apiservices.apiglobal.ApiServiceGlobalHelper
import com.example.coivd_19mvvm.data.WorldWideCases
import retrofit2.Response
import javax.inject.Inject

class GlobalImple
@Inject constructor(
    private val apiServiceGlobal: ApiServiceGlobal
) : ApiServiceGlobalHelper {

    override suspend fun getGlobalData(): Response<WorldWideCases> =
        apiServiceGlobal.getGlobalData()

}