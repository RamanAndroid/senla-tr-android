package com.example.senlastudy.presenter.rated

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieTopRatedPresenter(private val iMovieTopRatedView: MainContract.IMovieTopRatedView) :
    IMovieTopRatedPresenter {

    override fun downloadingMovieTopRatedList(language: String, page: Int) {


        MovieApplication.apiService.getTopRatedMovie(language, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> iMovieTopRatedView.setData(response.results) },
                { t -> iMovieTopRatedView.errorResponse(t) })

    }

    override fun attach() {

    }

    override fun detach() {

    }
}