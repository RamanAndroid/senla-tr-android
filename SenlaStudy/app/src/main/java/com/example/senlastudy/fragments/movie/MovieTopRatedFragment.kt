package com.example.senlastudy.fragments.movie

import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.presenter.rated.TopRatedPresenter


class MovieTopRatedFragment : BaseMovieListFragment() {
    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return TopRatedPresenter()
    }
}