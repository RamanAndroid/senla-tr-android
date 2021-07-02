package com.example.senlastudy.fragments.movie

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.senlastudy.R
import com.example.senlastudy.database.MovieDatabaseHelper
import com.example.senlastudy.databinding.FragmentMovieDetailBinding
import com.example.senlastudy.fragments.BaseFragment
import com.example.senlastudy.presenter.DetailMoviePresenter
import com.example.senlastudy.presenter.MovieDetailContract
import com.example.senlastudy.retrofit.pojo.TestMovie


class MovieDetailFragment :
    BaseFragment<MovieDetailContract.PresenterMovieDetail, MovieDetailContract.ViewMovieDetail>(),
    MovieDetailContract.ViewMovieDetail {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var movie: Int? = null

    companion object {
        const val MOVIE_EXTRA = "MOVIE_EXTRA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getInt(MOVIE_EXTRA) ?: error("cannot find movie id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovie()
        MovieDatabaseHelper(requireContext()).movieDatabaseHelper(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadMovie() {
        movie?.let { getPresenter().downloadingDetailsMovie(it) }
    }

    override fun createPresenter(): DetailMoviePresenter {
        return DetailMoviePresenter()
    }

    override fun setData(movie: TestMovie) {
        binding.apply {
            movie.let {
                Glide.with(requireContext()).load(it.image).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(
                        R.drawable.ic_error
                    )
                    .into(movieDetailImage)

                movieDetailName.text = it.title
                movieDetailFirstAirDate.text = it.releaseDate
                movieDetailOriginalName.text = it.originalTitle
                movieDetailOriginalLanguage.text = it.originalLanguage
            }
        }
    }

    override fun errorResponse(t: Throwable) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Информация об данном фильме не смогла загрузиться!")
        builder.setCancelable(false)
        builder.setPositiveButton("Окей") { dialogs, which ->
            activity?.onBackPressed()
            dialogs.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
    }

    override fun showViewLoading() {
        binding.downloadMovie.isVisible = true

    }

    override fun hideViewLoading() {
        binding.downloadMovie.isVisible = false
    }

}