package com.example.senlastudy.fragments

import androidx.fragment.app.Fragment
import com.example.senlastudy.presenter.MainContract

abstract class BaseFragment<Presenter: MainContract.Presenter<MainContract.View>>:Fragment() {

    private var presenter: Presenter? = null

    protected fun createPresenter(presenter: Presenter) {
        this.presenter = presenter
    }

    protected fun deletePresenter(){
        this.presenter = null
    }

    protected fun getPresenter(): Presenter {
        return presenter ?: error("Presenter is not created")
    }

}