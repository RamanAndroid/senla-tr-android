package com.example.senlastudy.presenter.playing

interface IMovieNowPlayingPresenter {
    fun downloadingMovieNowPlayingList(language: String, page: Int)
    fun attach()
    fun detach()
}