package com.example.cinestream.data.remote

import com.example.cinestream.data.Info.PopularMovie
import com.example.cinestream.data.Info.PopularMoviesResponse
import com.example.cinestream.data.Info.Trending
import com.example.cinestream.data.Info.TrendingResponse
import com.example.cinestream.data.Info.UpcomingMoviesResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("trending/movie/week")
    suspend fun getHome(): Response<TrendingResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Response<UpcomingMoviesResponse>

    @GET("movie/popular")
    suspend fun getPopulat(): Response<PopularMoviesResponse>
}