package com.example.senlastudy.presenter

import android.util.Log
import com.example.senlastudy.retrofit.api.RetrofitObject
import com.example.senlastudy.view.IMoviePopularView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePopularPresenter(var iMoviePopularView: IMoviePopularView) : IMoviePopularPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun downloadingMoviePopularList(language: String, page: Int) {
        compositeDisposable.add(
            RetrofitObject.apiService.getPopularMovie(language, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> iMoviePopularView.setupRecyclerView(response.results) },
                    { t -> Log.d("FAILURE", t.toString()) })
        )
    }
}