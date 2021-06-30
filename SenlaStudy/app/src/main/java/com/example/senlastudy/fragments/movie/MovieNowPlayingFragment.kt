package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.senlastudy.database.MovieDatabaseHelper
import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.presenter.playing.NowPlayingPresenter


class MovieNowPlayingFragment : BaseMovieListFragment() {

    override fun createPresenter(): MovieListContract.PresenterMovieList {
        return NowPlayingPresenter()
    }


}