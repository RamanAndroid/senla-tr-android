package com.example.senlastudy.fragments.movie

import androidx.fragment.app.Fragment
import com.example.senlastudy.presenter.IMoviePresenter

abstract class BaseMovieFragment<Presenter : IMoviePresenter> : Fragment() {


    private var presenter: Presenter? = null

    fun createPresenter(presenter: Presenter) {
        this.presenter = presenter
    }

    fun deletePresenter(){
        this.presenter = null
    }

    protected fun getPresenter(): Presenter {
        return presenter ?: error("Presenter is not created")
    }

    abstract fun setupRecyclerView()




}