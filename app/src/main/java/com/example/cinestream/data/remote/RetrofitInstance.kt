package com.example.cinestream.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: AnimeApi by lazy {
        Retrofit.Builder()
            .baseUrl(const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }
}