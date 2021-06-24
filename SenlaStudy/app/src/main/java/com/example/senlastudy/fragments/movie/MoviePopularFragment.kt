package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.databinding.FragmentMoviePopularBinding
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.presenter.popular.MoviePopularPresenter

class MoviePopularFragment : BaseMovieFragment() {

    lateinit var moviePopularPresenter: MoviePopularPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createPresenter(MoviePopularPresenter() as IMoviePresenter)
        moviePopularPresenter = getPresenter() as MoviePopularPresenter
        moviePopularPresenter.attachView(this)
        initializationAttributes()
        getMovie()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        deletePresenter()
        moviePopularPresenter.detach()
    }


    override fun getMovie() {
        moviePopularPresenter.downloadingMovieList(
            MovieApplication.localLanguage,
            getPage()
        )
    }
}