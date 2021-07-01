package com.example.senlastudy.presenter

import com.example.senlastudy.retrofit.pojo.Movie
import com.example.senlastudy.retrofit.pojo.TestMovie

interface MovieDetailContract {
    interface ViewMovieDetail : MainContract.View {
        fun setData(movie: TestMovie)
        fun errorResponse(t:Throwable)
    }

    interface PresenterMovieDetail :
        MainContract.Presenter<ViewMovieDetail> {
        fun downloadingDetailsMovie(movieId: Int)
    }
}