package com.example.senlastudy

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.NavigationFragment
import com.example.senlastudy.fragments.movie.BaseMovieListFragment
import com.example.senlastudy.fragments.movie.MovieDetailFragment


class MainActivity : AppCompatActivity(), BaseMovieListFragment.Navigator {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG_DETAIL_FRAGMENT = "DETAIL_FRAGMENT"
        private const val TAG_NAVIGATION_FRAGMENT = "NAVIGATION_FRAGMENT"
        private const val TAG_MOVIE_ID = "MOVIE_ID"
    }

    private var movieId: Int = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAG_MOVIE_ID, movieId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (savedInstanceState != null) {
            replaceDetailFragment(savedInstanceState.getInt(TAG_MOVIE_ID))
        } else {
            replaceNavigationFragment()
        }
    }

    override fun openMovieDetail(movieId: Int) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt(MovieDetailFragment.MOVIE_EXTRA, movieId)
        this.movieId = movieId
        val detailFragment = createFragment(TAG_DETAIL_FRAGMENT)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(
                R.id.fragment_container,
                detailFragment,
                TAG_DETAIL_FRAGMENT
            ).addToBackStack(null)
        } else {
            transaction.replace(
                R.id.fragment_container_overview, detailFragment,
                TAG_DETAIL_FRAGMENT
            ).addToBackStack(null)
        }
        detailFragment.arguments = bundle
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun replaceDetailFragment(movieId: Int) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt(MovieDetailFragment.MOVIE_EXTRA, movieId)
        val detailFragment = createFragment(TAG_DETAIL_FRAGMENT)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(
                R.id.fragment_container,
                detailFragment,
                TAG_DETAIL_FRAGMENT
            ).addToBackStack(null)
        } else {
            fragmentManager.popBackStack()
            transaction.replace(
                R.id.fragment_container_overview,
                detailFragment,
                TAG_DETAIL_FRAGMENT
            ).addToBackStack(null)
        }
        detailFragment.arguments = bundle
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun replaceNavigationFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        var nowDisplayedFragment = fragmentManager.findFragmentByTag(TAG_NAVIGATION_FRAGMENT)
        if (nowDisplayedFragment != null) {
            transaction.replace(
                R.id.fragment_container,
                nowDisplayedFragment,
                TAG_NAVIGATION_FRAGMENT
            )
        } else {
            nowDisplayedFragment = createFragment(TAG_NAVIGATION_FRAGMENT)
            transaction.replace(
                R.id.fragment_container,
                nowDisplayedFragment,
                TAG_NAVIGATION_FRAGMENT
            ).addToBackStack(null)
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun createFragment(tag: String): Fragment {
        return when (tag) {
            TAG_DETAIL_FRAGMENT -> {
                MovieDetailFragment()
            }
            TAG_NAVIGATION_FRAGMENT -> {
                NavigationFragment()
            }
            else -> error("Unexpected tag $tag")
        }

    }
}
