package com.example.cinestream.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object RetrofitInstance {
    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}