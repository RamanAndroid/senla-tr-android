package com.example.senlastudy.presenter.popular

import com.example.senlastudy.MovieApplication.Companion.apiService
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.presenter.MoviePresenter
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePopularPresenter(private val iMoviePopularView: MainContract.IMovieView) :
    MoviePresenter<MainContract.IMovieView>(), IMoviePresenter {

    private var disposables: CompositeDisposable? = null

    fun downloadingMovieList(language: String, page: Int) {

        disposables?.add(
            apiService.getPopularMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        iMoviePopularView.setData(response.results)
                    },
                    { t -> iMoviePopularView.errorResponse(t) })
        )

    }

    override fun attachView(view: MainContract.IMovieView) {
        super.attachView(view)
        disposables = CompositeDisposable()
    }

    override fun detach() {
        super.detach()
        disposables = null
    }


}