package com.example.senlastudy.view

import com.example.senlastudy.retrofit.pojo.Movie

interface IMoviePopularView {
    fun setupRecyclerView(movie: List<Movie>)
}