package com.example.senlastudy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.senlastudy.presenter.MainContract

/*
Базовый абстрактный класс для всех фрагментов
Занимается созданием Presenter а также возвращает созданный Presenter
 */
abstract class BaseFragment<Presenter : MainContract.Presenter<View>, View : MainContract.View> :
    Fragment() {

    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.attachView(getMvpView())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter = null
    }


    abstract fun createPresenter(): Presenter

    open fun getMvpView(): View{
        return this as? View ?: error("Cannot cast to view interface!")
    }

    protected fun getPresenter(): Presenter {
        return presenter ?: error("Presenter is not created")
    }

}