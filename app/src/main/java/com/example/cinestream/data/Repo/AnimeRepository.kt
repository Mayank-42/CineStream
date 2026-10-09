package com.example.cinestream.data.Repo

import com.example.cinestream.data.remote.AnimeApi

class AnimeRepository(private val getMessage: AnimeApi ) {

    suspend fun getTHeHomePage(){
        getMessage.getHome()
    }

}