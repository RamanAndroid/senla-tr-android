package com.example.senlastudy.presenter.movie

import com.example.senlastudy.presenter.MainContract
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