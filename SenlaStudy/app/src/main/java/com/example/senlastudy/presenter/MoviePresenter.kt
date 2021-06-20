package com.example.senlastudy.presenter

import com.example.senlastudy.view.MainContract

abstract class MoviePresenter<View : MainContract.IMovieView> : IMoviePresenter.Presenter<View> {
    private var view: View? = null

    override fun attachView(view: View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    protected fun getView(): View {
        return view ?: error("Presenter is not attached")
    }
}