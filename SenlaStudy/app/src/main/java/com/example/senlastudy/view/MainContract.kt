package com.example.senlastudy.view

import com.example.senlastudy.retrofit.pojo.Movie

interface MainContract {
    interface IMoviePopularView {
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }
    interface IMovieTopRatedView {
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }
    interface IMovieUpcomingView {
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }
    interface IMovieNowPlayingView {
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }
}