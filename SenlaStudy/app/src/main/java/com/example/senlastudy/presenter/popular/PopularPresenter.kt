package com.example.senlastudy.presenter.popular

import com.example.senlastudy.MovieApplication.Companion.apiService
import com.example.senlastudy.presenter.MainContract
import com.example.senlastudy.presenter.BasePresenter
import com.example.senlastudy.presenter.MovieListContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PopularPresenter : BasePresenter<MovieListContract.ViewMovieList>(),
    MovieListContract.PresenterMovieList {

    override fun downloadingMovieList(page: Int) {
        getCompositeDisposable().add(
            apiService.getPopularMovie(page)
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