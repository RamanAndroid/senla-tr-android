package com.example.senlastudy.presenter

import com.example.senlastudy.retrofit.pojo.Movie

interface MainContract {

    interface View

    interface Presenter<View : MainContract.View> {

        fun attachView(view: View)
        fun detach()
    }


}

