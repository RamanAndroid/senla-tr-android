package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.View
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.presenter.upcoming.BaseUpcomingPresenter


class MovieUpcomingFragment : BaseMovieListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovie()
    }

    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return BaseUpcomingPresenter()
    }

}