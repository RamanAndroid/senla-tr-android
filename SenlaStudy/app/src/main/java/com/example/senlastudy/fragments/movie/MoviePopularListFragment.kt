package com.example.senlastudy.fragments.movie

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.movie.MovieListContract
import com.example.senlastudy.presenter.movie.popular.PopularPresenter

class MoviePopularListFragment : BaseMovieListFragment() {
    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return PopularPresenter(MovieApplication.apiService)
    }
}