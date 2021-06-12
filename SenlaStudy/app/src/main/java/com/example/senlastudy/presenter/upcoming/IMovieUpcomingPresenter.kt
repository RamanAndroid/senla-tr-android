package com.example.senlastudy.presenter.upcoming

interface IMovieUpcomingPresenter {
    fun downloadingMovieUpcomingList(language: String, page: Int)
    fun attach()
    fun detach()
}