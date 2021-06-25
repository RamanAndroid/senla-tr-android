package com.example.senlastudy.presenter

import com.example.senlastudy.retrofit.pojo.Movie

interface MainContract {

    interface View

    interface Presenter<View : MainContract.View> {

        fun attachView(view: View)
        fun detach()
    }

    interface ViewMovieList:View{
        fun setData(movie: List<Movie>)
        fun errorResponse(error: Throwable)
    }

    interface PresenterMovieList<View:ViewMovieList>:Presenter<ViewMovieList>{
        fun downloadingMovieList(language:String, page:Int)
    }

    interface ViewMovieDetail:View{
        fun setData(movie:Movie)
    }

    interface PresenterMovieDetail<View:ViewMovieDetail>:Presenter<ViewMovieList>

}

