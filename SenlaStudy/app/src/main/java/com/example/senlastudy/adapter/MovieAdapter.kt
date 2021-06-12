package com.example.senlastudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.senlastudy.R
import com.example.senlastudy.databinding.RowMovieBinding
import com.example.senlastudy.retrofit.pojo.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movie = emptyList<Movie>()


    inner class MovieViewHolder(private val binding: RowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView).load(movie.image).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(
                        R.drawable.ic_error
                    )
                    .into(logoMovie)

                titleMovie.text = movie.original_title

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = movie[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = movie.size

    fun setData(movie:List<Movie>){

        this.movie=movie
    }

}