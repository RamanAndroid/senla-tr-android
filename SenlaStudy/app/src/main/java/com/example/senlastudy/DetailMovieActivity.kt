package com.example.senlastudy

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.senlastudy.databinding.ActivityDetailMovieBinding
import com.example.senlastudy.retrofit.pojo.Movie
import com.example.senlastudy.utils.Constants

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        unpackingMovie()
    }

    fun unpackingMovie() {
        val movie: Movie = intent.getParcelableExtra<Parcelable>(Constants.MOVIE_EXTRA) as Movie

        binding.apply {
            Glide.with(this@DetailMovieActivity).load(movie.image).centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(
                    R.drawable.ic_error
                )
                .into(movieDetailImage)

            movieDetailName.text = movie.title
            movieDetailFirstAirDate.text = movie.releaseDate
            movieDetailOriginalName.text = movie.originalTitle
            movieDetailOriginalLanguage.text = movie.originalLanguage

        }

    }
}