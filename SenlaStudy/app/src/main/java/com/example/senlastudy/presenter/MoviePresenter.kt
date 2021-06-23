package com.example.senlastudy.presenter

import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class MoviePresenter<View : MainContract.IMovieView> : IMoviePresenter.Presenter<View> {
    private var view: View? = null
    private var disposable: CompositeDisposable? = null


    override fun attachView(view: View) {
        this.disposable = CompositeDisposable()
        this.view = view
    }

    override fun detach() {
        this.disposable = null
        this.view = null
    }

    protected fun getView(): View {
        return view ?: error("Presenter is not attached")
    }

    protected fun getCompositeDisposable():CompositeDisposable{
        return disposable ?: error("CompositeDisposable is not created")
    }


}