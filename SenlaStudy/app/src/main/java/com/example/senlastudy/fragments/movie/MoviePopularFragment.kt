package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.databinding.FragmentMoviePopularBinding
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.presenter.popular.MoviePopularPresenter

class MoviePopularFragment : BaseMovieFragment() {

    lateinit var moviePopularPresenter: MoviePopularPresenter
    private var _binding: FragmentMoviePopularBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviePopularBinding.inflate(inflater, container, false)


        createPresenter(MoviePopularPresenter() as IMoviePresenter)
        moviePopularPresenter = getPresenter() as MoviePopularPresenter
        moviePopularPresenter.attachView(this)
        initializationAttributes()
        getMovie()

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        deletePresenter()
        moviePopularPresenter.detach()
    }


    override fun getMovie() {
        moviePopularPresenter.downloadingMovieList(
            MovieApplication.localLanguage,
            getPage()
        )
    }
}