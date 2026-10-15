package com.example.cinestream.data.remote

import com.example.cinestream.data.Info.Trending
import com.example.cinestream.data.Info.TrendingResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("trending/movie/week")
    suspend fun getHome(): Response<TrendingResponse>
}