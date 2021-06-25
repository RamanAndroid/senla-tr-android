package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.View
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.popular.MoviePopularPresenter

class MoviePopularListFragment : BaseMovieListFragment() {

    lateinit var moviePopularPresenter: MoviePopularPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createPresenter(MoviePopularPresenter())
        getPresenter().attachView(this)
        getMovie()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        deletePresenter()
        getPresenter().detach()
    }


    override fun getMovie() {
        moviePopularPresenter.downloadingMovieList(
            MovieApplication.localLanguage,
            getPage()
        )
    }
}