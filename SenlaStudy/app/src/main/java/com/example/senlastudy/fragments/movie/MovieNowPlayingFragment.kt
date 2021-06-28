package com.example.senlastudy.fragments.movie

import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.presenter.playing.NowPlayingPresenter


class MovieNowPlayingFragment : BaseMovieListFragment() {



    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return NowPlayingPresenter()
    }


}