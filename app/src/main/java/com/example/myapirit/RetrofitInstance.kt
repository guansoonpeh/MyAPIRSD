package com.example.myapirit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(MyRetAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:MyRetAPI by lazy {
        retrofit.create(MyRetAPI::class.java)
    }
}