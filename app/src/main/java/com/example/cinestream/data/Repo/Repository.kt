package com.example.cinestream.data.Repo

import com.example.cinestream.data.Info.PopularMovie
import com.example.cinestream.data.Info.Trending
import com.example.cinestream.data.Info.TrendingResponse
import com.example.cinestream.data.Info.UpcomingMoviesResponse
import com.example.cinestream.data.remote.Api
import kotlinx.coroutines.flow.Flow

class Repository(private val getMessage: Api ) {

    suspend fun getTHeHomePage(): TrendingResponse{
        val response = getMessage.getHome()

        if (!response.isSuccessful) {
            throw Exception("API error: ${response.code()}\n ${response.body()}")
        }

        return response.body()
            ?: throw Exception("Home response is empty")
    }

    suspend fun getUpcomming(): UpcomingMoviesResponse{
        val response= getMessage.getUpcoming()
        if (!response.isSuccessful) {
            throw Exception("API error: ${response.code()}\n ${response.body()}")
        }

        return response.body()
            ?: throw Exception("Home response is empty")
    }

    suspend fun getPopular(): PopularMovie{
        val response= getMessage.getPopulat()
        if (!response.isSuccessful) {
            throw Exception("API error: ${response.code()}\n ${response.body()}")
        }

        return response.body()
            ?: throw Exception("Home response is empty")
    }



}