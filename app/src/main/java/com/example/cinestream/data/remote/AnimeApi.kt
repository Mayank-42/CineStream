package com.example.cinestream.data.remote

import retrofit2.http.GET

interface AnimeApi {

    @GET("api/home")
    suspend fun getHome():
}