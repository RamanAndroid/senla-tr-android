package com.example.senlastudy.presenter

import android.util.Log
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.database.MovieDatabaseHelper
import com.example.senlastudy.retrofit.pojo.TestMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.coroutines.suspendCoroutine


class DetailMoviePresenter : BasePresenter<MovieDetailContract.ViewMovieDetail>(),
    MovieDetailContract.PresenterMovieDetail {

    override fun downloadingDetailsMovie(movieId: Int) {
        val createObserver = Observable.create(ObservableOnSubscribe<TestMovie> { emitter ->
            var movieTest = MovieDatabaseHelper.selectByFieldValue("movies", "title", movieId)
            if (movieTest != null) {
                emitter.onNext(movieTest)
            } else {

               val response = MovieApplication.apiService.getMovie(movieId)

                /*
                MovieDatabaseHelper.insertMovie(movieTest)
                movieTest = MovieDatabaseHelper.selectByFieldValue("movies", "title", movieId)

                 */
                emitter.onNext(movieTest)
            }
            emitter.onComplete()
        })

        createObserver.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { getView().hideViewLoading() }
            .doFinally { getView().hideViewLoading() }
            .subscribe({ movieTest ->
                getView().setData(movieTest)
            }, { t ->
                Log.d("ERROR", t.toString())
                getView().errorResponse(t)
            })
    }
}