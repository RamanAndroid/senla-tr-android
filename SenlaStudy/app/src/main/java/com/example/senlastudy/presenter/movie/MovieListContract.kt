package com.example.senlastudy.presenter.movie

import com.example.senlastudy.presenter.MainContract
import com.example.senlastudy.retrofit.pojo.Movie

interface MovieListContract {
    interface ViewMovieList: MainContract.View {
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }

    interface PresenterMovieList: MainContract.Presenter<ViewMovieList> {
        fun downloadingMovieList()
    }

}