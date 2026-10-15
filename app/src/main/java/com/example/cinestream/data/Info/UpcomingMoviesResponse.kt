package com.example.cinestream.data.Info

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesResponse(
    val dates: UpcomingDates?,
    val page: Int,
    val results: List<UpcomingMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
