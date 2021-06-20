package com.example.senlastudy.presenter

import com.example.senlastudy.retrofit.pojo.Movie
import com.example.senlastudy.view.MainContract

interface IMoviePresenter {

    interface View

    interface Presenter<View : MainContract.IMovieView> {

        fun attachView(view: View)
        fun detach()
    }

}