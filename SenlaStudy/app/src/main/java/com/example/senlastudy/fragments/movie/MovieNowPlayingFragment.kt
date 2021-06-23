package com.example.senlastudy.fragments.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senlastudy.DetailMovieActivity
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.adapter.MovieAdapter
import com.example.senlastudy.databinding.FragmentMovieNowPlayingBinding
import com.example.senlastudy.presenter.playing.MovieNowPlayingPresenter
import com.example.senlastudy.retrofit.pojo.Movie
import com.example.senlastudy.view.MainContract


class MovieNowPlayingFragment : Fragment(), MainContract.IMovieView, MovieAdapter.OnMovieClickListener {

    val movieNowPlayingPresenter: MovieNowPlayingPresenter by lazy { MovieNowPlayingPresenter(this) }
    private val adapter: MovieAdapter by lazy { MovieAdapter(this) }
    private var _binding: FragmentMovieNowPlayingBinding? = null
    private val binding get() = _binding!!
    private var page = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieNowPlayingBinding.inflate(inflater, container, false)


        setupRecyclerView()
        movieNowPlayingPresenter.downloadingMovieList(
            MovieApplication.localLanguage,
            page
        )


        return binding.root

    }

    private fun setupRecyclerView() {
        binding.apply {
            rvMovieList.adapter = adapter
            rvMovieList.setHasFixedSize(true)
            rvMovieList.layoutManager =
                LinearLayoutManager(requireContext())
            rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        page++
                        movieNowPlayingPresenter.downloadingMovieList(
                            MovieApplication.localLanguage,
                            page
                        )
                    }
                }

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        movieNowPlayingPresenter.detach()
    }

    override fun setData(movie: List<Movie>) {
        binding.noMovie.isVisible = false
        binding.rvMovieList.isVisible = true
        adapter.setData(movie)
        adapter.notifyDataSetChanged()
    }

    override fun errorResponse(error: Throwable) {
        binding.noMovie.isVisible = true
        binding.rvMovieList.isVisible = false
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.MOVIE_EXTRA, movie)
        startActivity(intent)
    }


}