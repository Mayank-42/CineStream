package com.example.cinestream.data.Info

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val page: Int,
    val results: List<Trending>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)
