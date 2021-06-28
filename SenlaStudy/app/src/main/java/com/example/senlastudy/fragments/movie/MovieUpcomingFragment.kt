package com.example.senlastudy.fragments.movie

import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.presenter.upcoming.UpcomingPresenter


class MovieUpcomingFragment : BaseMovieListFragment() {



    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return UpcomingPresenter()
    }

}