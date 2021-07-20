package com.example.senlastudy.fragments.movie

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.R
import com.example.senlastudy.ShareActivity
import com.example.senlastudy.databinding.FragmentMovieDetailBinding
import com.example.senlastudy.fragments.BaseFragment
import com.example.senlastudy.presenter.movie.DetailMoviePresenter
import com.example.senlastudy.presenter.movie.MovieDetailContract
import com.example.senlastudy.retrofit.pojo.DetailsMovie


class MovieDetailFragment :
    BaseFragment<MovieDetailContract.PresenterMovieDetail, MovieDetailContract.ViewMovieDetail>(),
    MovieDetailContract.ViewMovieDetail {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var movieId: Int? = null
    private var movieTitle: String = ""
    private var movieVoteAverage: String = ""
    private var movieVoteCount: String = ""
    private var dialog: AlertDialog? = null

    companion object {
        const val MOVIE_EXTRA = "MOVIE_EXTRA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt(MOVIE_EXTRA) ?: error("cannot find movie id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovie()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog?.dismiss()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.btnOpenShareActivity.setOnClickListener {
            openShareActivity()
        }
    }

    private fun loadMovie() {
        movieId?.let { getPresenter().downloadingDetailsMovie(it) }
    }

    override fun createPresenter(): DetailMoviePresenter {
        return DetailMoviePresenter(
            movieDetailsDao = MovieApplication.movieDetailsDaoImplDao,
            apiService = MovieApplication.apiService
        )
    }

    override fun setData(movie: DetailsMovie) {
        movieTitle = movie.title
        movieVoteAverage = movie.voteAverage
        movieVoteCount = movie.voteCount
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
        builder.setPositiveButton("Окей") { dialogs, _ ->
            activity?.onBackPressed()
            dialogs.dismiss()
        }
        dialog = builder.create()
        dialog?.let {
            it.show()
            it.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            it.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        }
    }

    override fun showViewLoading() {
        binding.downloadMovie.isVisible = true
    }

    override fun hideViewLoading() {
        binding.downloadMovie.isVisible = false
    }

    private fun openShareActivity() {
        val intent = Intent(requireContext(), ShareActivity::class.java)
        intent.putExtra(ShareActivity.MOVIE_EXTRA_TITLE,movieTitle)
        intent.putExtra(ShareActivity.MOVIE_EXTRA_VOTE_COUNT,movieVoteCount)
        intent.putExtra(ShareActivity.MOVIE_EXTRA_VOTE_AVERAGE,movieVoteAverage )
        startActivity(intent)
    }

}