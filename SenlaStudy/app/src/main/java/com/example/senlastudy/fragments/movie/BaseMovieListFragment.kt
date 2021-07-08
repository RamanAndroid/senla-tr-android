package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senlastudy.adapter.MovieAdapter
import com.example.senlastudy.databinding.FragmentMovieListBinding
import com.example.senlastudy.fragments.BaseFragment
import com.example.senlastudy.presenter.MovieListContract
import com.example.senlastudy.retrofit.pojo.Movie


/*
Базовый абстрактный класс для Fragment который выводит фильмы в RecyclerView
Данный класс занимается инициализацией Layout на который выводится информация
setData - заносит данные  в RecyclerVIew
errorResponse -  выводит информацию об неудачной загрузки данных
onMOvieClick - открывает Fragment с полной информацией об Фильме
getPage - возвращает текущий номер страницы
getMovie - кидает запрос на скачивание новой страницы с фильмами
 */
abstract class BaseMovieListFragment :
    BaseFragment<MovieListContract.PresenterMovieList, MovieListContract.ViewMovieList>(),
    MovieListContract.ViewMovieList,
    MovieAdapter.OnMovieClickListener {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val adapter: MovieAdapter by lazy { MovieAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializationAttributes()
        getMovie()
    }

    private fun initializationAttributes() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.setHasFixedSize(true)
        binding.rvMovieList.layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    getMovie()
                }
            }
        })
    }

    override fun showViewLoading() {
        binding.rvMovieList.isVisible = false
        binding.downloadMovie.isVisible = true
    }

    override fun hideViewLoading() {
        binding.rvMovieList.isVisible = true
        binding.downloadMovie.isVisible = false
    }

    override fun setData(movie: List<Movie>) {
        adapter.setData(movie)
        adapter.notifyDataSetChanged()
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
        val activity = (context as Navigator)
        activity.openMovieDetail(movie.id)
    }

    private fun getMovie() {
        getPresenter().downloadingMovieList()
    }

    interface Navigator {
        fun openMovieDetail(movieId: Int)
    }

}