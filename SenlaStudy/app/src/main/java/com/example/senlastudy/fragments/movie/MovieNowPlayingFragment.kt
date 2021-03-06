package com.example.senlastudy.fragments.movie

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.movie.MovieListContract
import com.example.senlastudy.presenter.movie.playing.NowPlayingPresenter


class MovieNowPlayingFragment : BaseMovieListFragment() {
    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return NowPlayingPresenter(MovieApplication.apiService)
    }
}