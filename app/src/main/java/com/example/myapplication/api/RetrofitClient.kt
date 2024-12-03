package com.example.myapplication.api

import com.example.myapplication.constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apiService: TheCoachApiService by lazy {
        RetrofitClient.retrofit.create(TheCoachApiService::class.java)
    }
}