package com.example.senlastudy.presenter.rated

import com.example.senlastudy.MovieApplication
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.view.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MovieTopRatedPresenter(private val iMovieTopRatedView: MainContract.IMovieView) :
    IMoviePresenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun downloadingMovieList(language: String, page: Int) {


        disposables.add(
            MovieApplication.apiService.getTopRatedMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> iMovieTopRatedView.setData(response.results) },
                    { t -> iMovieTopRatedView.errorResponse(t) })
        )

    }




    override fun detach() {
        disposables.dispose()
    }
}