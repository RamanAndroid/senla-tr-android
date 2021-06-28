package com.example.senlastudy.presenter

import com.example.senlastudy.MovieApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailMoviePresenter : BasePresenter<MovieDetailContract.ViewMovieDetail>(),
    MovieDetailContract.PresenterMovieDetail {

    override fun downloadingDetailsMovie(movieId: Int) {
        getCompositeDisposable().add(
            MovieApplication.apiService.getMovie(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> getView().setData(response) },
                    { t -> getView().errorResponse(t) })
        )
    }

}