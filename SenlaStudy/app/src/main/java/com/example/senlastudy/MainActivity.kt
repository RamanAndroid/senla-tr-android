package com.example.senlastudy

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.adapter.MovieFragmentPagerAdapter
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.NavigationFragment
import com.example.senlastudy.fragments.movie.BaseMovieListFragment
import com.example.senlastudy.fragments.movie.MovieDetailFragment
import com.example.senlastudy.retrofit.pojo.Movie


class MainActivity : AppCompatActivity(), BaseMovieListFragment.Navigator {

    private lateinit var binding: ActivityMainBinding
    private val adapter: MovieFragmentPagerAdapter by lazy {
        MovieFragmentPagerAdapter(
            supportFragmentManager
        )
    }

    private val movieDetailFragment = MovieDetailFragment()
    private val navigationFragment = NavigationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, navigationFragment)
        fragmentTransaction.commit()
    }

    override fun openMovieDetail(movie: Movie) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt(MovieDetailFragment.MOVIE_EXTRA, movie.id)
        movieDetailFragment.arguments = bundle
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(R.id.fragment_container, movieDetailFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        } else {
            transaction.replace(R.id.fragment_container_overview, movieDetailFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        }
    }
}

