package com.example.senlastudy.presenter.playing

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.BasePresenter
import com.example.senlastudy.presenter.MovieListContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BaseNowPlayingPresenter : BasePresenter<MovieListContract.ViewMovieList>(),
    MovieListContract.PresenterMovieList {

    override fun downloadingMovieList(page: Int) {
        getCompositeDisposable().add(
            MovieApplication.apiService.getNowPlayingMovie(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> getView().setData(response.results) },
                    { t -> getView().errorResponse(t) })
        )
    }
}