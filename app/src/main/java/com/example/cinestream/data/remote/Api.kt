package com.example.cinestream.data.remote

import com.example.cinestream.data.Info.Trending
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("api/home")
    suspend fun getHome(): Response<List<Trending>>
}