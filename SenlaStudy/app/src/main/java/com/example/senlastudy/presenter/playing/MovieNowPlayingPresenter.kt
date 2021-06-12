package com.example.senlastudy.presenter.playing

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieNowPlayingPresenter(private val iMovieNowPlayingView: MainContract.IMovieNowPlayingView) :
    IMovieNowPlayingPresenter {

    override fun downloadingMovieNowPlayingList(language: String, page: Int) {

        MovieApplication.apiService.getNowPlayingMovie(language, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> iMovieNowPlayingView.setData(response.results) },
                { t -> iMovieNowPlayingView.errorResponse(t) })

    }

    override fun attach() {

    }

    override fun detach() {

    }
}