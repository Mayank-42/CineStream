package com.example.cinestream.data.remote

import com.google.gson.JsonObject
import retrofit2.http.GET

interface AnimeApi {

    @GET("api/home")
    suspend fun getHome(): JsonObject
}