package com.example.senlastudy.presenter

interface IMoviePresenter {
    fun downloadingMovieList(language: String, page: Int)
    fun detach()
}