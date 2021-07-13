package com.example.senlastudy.fragments.movie

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.movie.MovieListContract
import com.example.senlastudy.presenter.movie.upcoming.UpcomingPresenter


class MovieUpcomingFragment : BaseMovieListFragment() {
    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return UpcomingPresenter(MovieApplication.apiService)
    }
}