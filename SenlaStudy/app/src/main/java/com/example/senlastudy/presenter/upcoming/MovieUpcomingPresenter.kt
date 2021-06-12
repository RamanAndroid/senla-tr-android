package com.example.senlastudy.presenter.upcoming

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieUpcomingPresenter(private val iMovieUpcomingView: MainContract.IMovieUpcomingView) :
    IMovieUpcomingPresenter {

    override fun downloadingMovieUpcomingList(language: String, page: Int) {

        MovieApplication.apiService.getUpcomingMovie(language, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> iMovieUpcomingView.setData(response.results) },
                { t -> iMovieUpcomingView.errorResponse(t) })
    }

    override fun attach() {

    }

    override fun detach() {

    }
}