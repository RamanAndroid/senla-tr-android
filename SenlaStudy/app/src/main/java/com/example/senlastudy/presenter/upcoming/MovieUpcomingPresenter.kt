package com.example.senlastudy.presenter.upcoming

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieUpcomingPresenter(private val iMovieUpcomingView: MainContract.IMovieView) :
    IMoviePresenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun downloadingMovieList(language: String, page: Int) {

        disposables.add(
            MovieApplication.apiService.getUpcomingMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> iMovieUpcomingView.setData(response.results) },
                    { t -> iMovieUpcomingView.errorResponse(t) })
        )
    }



    override fun detach() {
        disposables.dispose()
    }
}