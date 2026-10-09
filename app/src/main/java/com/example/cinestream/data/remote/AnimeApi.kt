package com.example.cinestream.data.remote

import com.example.cinestream.data.Info.HomeResponse
import com.google.gson.JsonObject
import okhttp3.Response
import retrofit2.http.GET

interface AnimeApi {

    @GET("api/home")
    suspend fun getHome(): Response<List<HomeResponse>>
}