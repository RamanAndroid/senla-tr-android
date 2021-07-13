package com.example.senlastudy.fragments.movie

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.movie.MovieListContract
import com.example.senlastudy.presenter.movie.rated.TopRatedPresenter


class MovieTopRatedFragment : BaseMovieListFragment() {
    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return TopRatedPresenter(MovieApplication.apiService)
    }
}