package com.example.senlastudy.presenter.upcoming

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.MainContract
import com.example.senlastudy.presenter.MoviePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieUpcomingPresenter(private val iMovieUpcomingView: MainContract.IMovieView) :
    MoviePresenter<MainContract.IMovieView>(), MainContract {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun downloadingMovieList(language: String, page: Int) {

        disposables.add(
            MovieApplication.apiService.getUpcomingMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> iMovieUpcomingView.setData(response.results) },
                    { t -> iMovieUpcomingView.errorResponse(t) })
        )
    }


}