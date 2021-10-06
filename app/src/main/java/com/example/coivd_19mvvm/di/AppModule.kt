package com.example.coivd_19mvvm.di

import com.example.coivd_19mvvm.apiservices.ApiServiceGlobal
import com.example.coivd_19mvvm.apiservices.ApiServiceGlobalHelper
import com.example.coivd_19mvvm.apiservices.GlobalImple
import com.example.coivd_19mvvm.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    val client = OkHttpClient().newBuilder().callTimeout(1, TimeUnit.MINUTES).build()

    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun providesGlobalApiService(retrofit: Retrofit) = retrofit.create(ApiServiceGlobal::class.java)

    @Provides
    @Singleton
    fun providesGlobalHelper(globalImple: GlobalImple): ApiServiceGlobalHelper = globalImple
}