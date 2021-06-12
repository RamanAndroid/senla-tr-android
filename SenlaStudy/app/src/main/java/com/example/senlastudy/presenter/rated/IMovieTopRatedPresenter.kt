package com.example.senlastudy.presenter.rated

interface IMovieTopRatedPresenter {
    fun downloadingMovieTopRatedList(language: String, page: Int)
    fun attach()
    fun detach()
}