package com.example.coivd_19mvvm.repository

import com.example.coivd_19mvvm.apiservices.ApiServiceGlobalHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiServiceGlobalHelper: ApiServiceGlobalHelper
) {
    suspend fun getGlobalData() = apiServiceGlobalHelper.getGlobalData()
}