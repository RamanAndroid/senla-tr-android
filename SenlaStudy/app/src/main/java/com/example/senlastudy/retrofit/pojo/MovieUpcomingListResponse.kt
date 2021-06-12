package com.example.senlastudy.retrofit.pojo

data class MovieUpcomingListResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)