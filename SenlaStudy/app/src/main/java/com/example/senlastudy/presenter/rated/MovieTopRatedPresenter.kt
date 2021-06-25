package com.example.senlastudy.presenter.rated

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.MainContract
import com.example.senlastudy.presenter.MoviePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MovieTopRatedPresenter(private val iMovieTopRatedView: MainContract.IMovieView) :   MoviePresenter<MainContract.IMovieView>(), MainContract {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun downloadingMovieList(language: String, page: Int) {


        disposables.add(
            MovieApplication.apiService.getTopRatedMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> iMovieTopRatedView.setData(response.results) },
                    { t -> iMovieTopRatedView.errorResponse(t) })
        )

    }


}