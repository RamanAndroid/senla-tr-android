package com.example.senlastudy.presenter.popular

import com.example.senlastudy.MovieApplication.Companion.apiService
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.presenter.MoviePresenter
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePopularPresenter :
    MoviePresenter<MainContract.IMovieView>(), IMoviePresenter {

    fun downloadingMovieList(language: String, page: Int) {
        getCompositeDisposable().add(
            apiService.getPopularMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        getView().setData(response.results)
                    },
                    { t -> getView().errorResponse(t) })
        )

    }


}