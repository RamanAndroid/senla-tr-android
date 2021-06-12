package com.example.senlastudy.presenter.popular

interface IMoviePopularPresenter {
    fun downloadingMoviePopularList(language: String, page: Int)
    fun attach()
    fun detach()
}