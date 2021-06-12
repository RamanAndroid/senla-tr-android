package com.example.senlastudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.senlastudy.adapter.MovieAdapter
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.presenter.MoviePopularPresenter
import com.example.senlastudy.retrofit.pojo.Movie
import com.example.senlastudy.utils.Constants
import com.example.senlastudy.view.IMoviePopularView

class MainActivity : AppCompatActivity(), IMoviePopularView {

    lateinit var moviePopularPresenter: MoviePopularPresenter
    private val adapter: MovieAdapter by lazy { MovieAdapter() }
    private lateinit var binding: ActivityMainBinding
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviePopularPresenter = MoviePopularPresenter(this)

        moviePopularPresenter.downloadingMoviePopularList(Constants.API_LANGUAGE, page)
    }

    override fun setupRecyclerView(movie: List<Movie>) {
        binding.apply {
            adapter.setData(movie)
            rvMovieList.adapter = adapter
            rvMovieList.setHasFixedSize(true)
            adapter.notifyDataSetChanged()
            rvMovieList.invalidate()
        }
    }
}