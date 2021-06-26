package com.example.senlastudy.presenter

import com.example.senlastudy.retrofit.pojo.Movie

interface MovieDetailContract {
    interface ViewMovieDetail : MainContract.View {
        fun setData(movie: Movie)
    }

    interface PresenterMovieDetail<View : ViewMovieDetail> :
        MainContract.Presenter<MovieListContract.ViewMovieList>
}