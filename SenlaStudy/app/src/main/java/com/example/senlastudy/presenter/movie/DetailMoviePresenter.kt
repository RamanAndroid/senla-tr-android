package com.example.senlastudy.presenter.movie

import android.util.Log
import com.example.senlastudy.database.dao.moviedao.MovieDetailsDao
import com.example.senlastudy.presenter.BasePresenter
import com.example.senlastudy.retrofit.api.ApiMovie
import com.example.senlastudy.retrofit.pojo.DetailsMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*


class DetailMoviePresenter(
    private val movieDetailsDao: MovieDetailsDao,
    private val apiService: ApiMovie
) :
    BasePresenter<MovieDetailContract.ViewMovieDetail>(),
    MovieDetailContract.PresenterMovieDetail {

    override fun downloadingDetailsMovie(movieId: Int) {
        val createObserver = Observable.create(ObservableOnSubscribe<DetailsMovie> { emitter ->
            val movieTest = movieDetailsDao.findById(movieId.toString())
            if (movieTest != null) {
                val pastTime = movieTest.recordingTime.time
                val currentTime = Date().time
                val seconds = (currentTime - pastTime)
                if (seconds >= 259200) {
                    val response = apiService.getMovie(movieId).execute()
                    val body = response.body()
                    if (body == null || !response.isSuccessful) {
                        emitter.onError(Throwable("The response from the server occurred with an error!"))
                    } else {
                        emitter.onNext(body)
                        movieDetailsDao.update(body)
                    }
                } else {
                    emitter.onNext(movieTest)
                }
            } else {
                val response = apiService.getMovie(movieId).execute()
                val body = response.body()
                if (body == null || !response.isSuccessful) {
                    emitter.onError(Throwable("The response from the server occurred with an error!"))
                } else {
                    emitter.onNext(body)
                    movieDetailsDao.put(body)
                }
            }
            emitter.onComplete()
        })

        createObserver.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                if (isViewAttached()) {
                    getView().showViewLoading()
                }
            }
            .doFinally {
                if (isViewAttached()) {
                    getView().hideViewLoading()
                }
            }
            .subscribe({ movieTest ->
                if (isViewAttached()) {
                    getView().setData(movieTest)
                }
            }, { t ->
                if (isViewAttached()) {
                    Log.e("Presenter", "Failed retrieve movie datails ", t)
                    getView().errorResponse(t)
                }
            })
    }
}