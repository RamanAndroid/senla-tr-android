package com.example.senlastudy.presenter.popular

import com.example.senlastudy.presenter.BasePresenter
import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.retrofit.api.ApiMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PopularPresenter(private val apiService: ApiMovie) :
    BasePresenter<MovieListContract.ViewMovieList>(),
    MovieListContract.PresenterMovieList {

    private var isLoading = false
    private var page = 1

    override fun downloadingMovieList() {
        if (!isLoading) {
            isLoading = true
            getView().showViewLoading()
            getCompositeDisposable().add(
                apiService.getPopularMovie(page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { response ->
                            page++
                            isLoading = false
                            getView().hideViewLoading()
                            getView().setData(response.results)
                        },
                        { t ->
                            isLoading = false
                            getView().errorResponse(t)
                            getView().hideViewLoading()
                        })
            )
        }
    }
}