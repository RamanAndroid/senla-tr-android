package com.example.senlastudy.fragments.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senlastudy.DetailMovieActivity
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.adapter.MovieAdapter
import com.example.senlastudy.databinding.FragmentMoviePopularBinding
import com.example.senlastudy.fragments.BaseFragment
import com.example.senlastudy.presenter.IMoviePresenter
import com.example.senlastudy.presenter.MoviePresenter
import com.example.senlastudy.retrofit.pojo.Movie
import com.example.senlastudy.view.MainContract

abstract class BaseMovieFragment : BaseFragment<IMoviePresenter>(), MainContract.IMovieView,MovieAdapter.OnMovieClickListener{

    private val rvMovie:RecyclerView? = null
    private var page = 1
    private var _binding: FragmentMoviePopularBinding? = null
    private val binding get() = _binding!!
    private val adapter: MovieAdapter by lazy { MovieAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviePopularBinding.inflate(inflater, container, false)

        return binding.root
    }

    protected fun initializationAttributes(){
        rvMovie?.let {
            rvMovie.adapter = adapter
            rvMovie.setHasFixedSize(true)
            rvMovie.layoutManager =
                LinearLayoutManager(requireContext())
            rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        page++
                        getPage()
                    }
                }

            })
        }
    }

    override fun setData(movie: List<Movie>) {
        adapter.setData(movie)
        adapter.notifyDataSetChanged()
        binding.noMovie.isVisible = false
        binding.rvMovieList.isVisible = true
    }

    override fun errorResponse(error: Throwable) {
        binding.noMovie.isVisible = true
        binding.rvMovieList.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.MOVIE_EXTRA, movie)
        startActivity(intent)
    }

    protected fun getPage():Int{
        return page
    }

    abstract fun getMovie()

}