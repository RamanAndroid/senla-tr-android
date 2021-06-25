package com.example.senlastudy.presenter.popular

import com.example.senlastudy.MovieApplication.Companion.apiService
import com.example.senlastudy.presenter.MainContract
import com.example.senlastudy.presenter.MoviePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePopularPresenter : MoviePresenter<MainContract.ViewMovieList>(),MainContract.PresenterMovieList<MainContract.ViewMovieList> {

   override fun downloadingMovieList(language: String, page: Int) {
        getCompositeDisposable().add(
            apiService.getPopularMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        getView().setData(response.results)
                    },
                    { t -> getView().errorResponse(t) })
        )

    }


}