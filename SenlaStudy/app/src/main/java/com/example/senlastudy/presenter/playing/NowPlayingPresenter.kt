package com.example.senlastudy.presenter.playing

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.BasePresenter
import com.example.senlastudy.presenter.MovieListContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NowPlayingPresenter : BasePresenter<MovieListContract.ViewMovieList>(),
    MovieListContract.PresenterMovieList {

    override fun downloadingMovieList(page: Int) {
        getView().showViewLoading()
        getCompositeDisposable().add(
            MovieApplication.apiService.getNowPlayingMovie(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        getView().setData(response.results)
                        getView().hideViewLoading()
                    },
                    { t ->
                        getView().errorResponse(t)
                        getView().hideViewLoading()
                    })
        )
    }
}