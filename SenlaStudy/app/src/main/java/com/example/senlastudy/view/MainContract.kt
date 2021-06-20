package com.example.senlastudy.view

import com.example.senlastudy.retrofit.pojo.Movie

interface MainContract {

    interface IMovieView {
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }
}