package com.example.cinestream.data.Repo

import com.example.cinestream.data.Info.HomeResponse
import com.example.cinestream.data.remote.AnimeApi

class AnimeRepository(private val getMessage: AnimeApi ) {

    suspend fun getTHeHomePage(): HomeResponse {
        val response = getMessage.getHome()

        if (!response.isSuccessful) {
            throw Exception("API error: ${response.code()}\n ${response.body()}")
        }

        return response.body()
            ?: throw Exception("Home response is empty")
    }

}