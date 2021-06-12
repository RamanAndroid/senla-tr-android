package com.example.senlastudy.presenter.popular

import com.example.senlastudy.MovieApplication.Companion.apiService
import com.example.senlastudy.presenter.popular.IMoviePopularPresenter
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePopularPresenter(private val iMoviePopularView: MainContract.IMoviePopularView) :
    IMoviePopularPresenter {

    override fun downloadingMoviePopularList(language: String, page: Int) {

        apiService.getPopularMovie(language, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> iMoviePopularView.setData(response.results) },
                { t -> iMoviePopularView.errorResponse(t) })

    }

    override fun attach() {
    }

    override fun detach() {
    }

}