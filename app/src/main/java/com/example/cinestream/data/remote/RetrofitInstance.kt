package com.example.cinestream.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java
import com.example.cinestream.BuildConfig

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${BuildConfig.TMDB_READ_ACCESS_TOKEN}"
                )
                .addHeader("accept", "application/json")
                .build()

            chain.proceed(request)
        }
        .build()
    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(const.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}