package com.example.senlastudy.presenter.movie.playing


import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.BasePresenter
import com.example.senlastudy.presenter.movie.MovieListContract
import com.example.senlastudy.retrofit.api.ApiMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NowPlayingPresenter(private val apiService: ApiMovie) :
    BasePresenter<MovieListContract.ViewMovieList>(),
    MovieListContract.PresenterMovieList {

    private var isLoading = false
    private var page = 1

    override fun downloadingMovieList() {
        if (!isLoading) {
            isLoading = true
            getView().showViewLoading()
            getCompositeDisposable().add(
               apiService.getNowPlayingMovie(page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { response ->
                            isLoading = false
                            page++
                            getView().setData(response.results)
                            getView().hideViewLoading()
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