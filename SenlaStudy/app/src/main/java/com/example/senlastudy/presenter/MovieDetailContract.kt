package com.example.senlastudy.presenter

import com.example.senlastudy.retrofit.pojo.DetailsMovie

interface MovieDetailContract {
    interface ViewMovieDetail : MainContract.View {
        fun setData(movie: DetailsMovie)
        fun errorResponse(t:Throwable)
    }

    interface PresenterMovieDetail :
        MainContract.Presenter<ViewMovieDetail> {
        fun downloadingDetailsMovie(movieId: Int)
    }
}