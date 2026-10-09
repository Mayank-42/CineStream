package com.example.cinestream.data.Info

data class PopularAnime(
    val id: String,
    val data_id: String?,
    val poster: String?,
    val title: String,
    val jname: String?,
    val description: String?,
    val tvInfo: PopularTvInfo?,
    val adultContent: Boolean?
)
