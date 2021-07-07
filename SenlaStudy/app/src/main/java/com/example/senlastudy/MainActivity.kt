package com.example.senlastudy

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    companion object {
        private const val tagDetail = "DETAIL_FRAGMENT"
        private const val tagNavigation = "NAVIGATION_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        replaceFragment(tagNavigation)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val detailFragment = fragmentManager.findFragmentByTag(tagDetail)
        if (detailFragment != null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                transaction.replace(R.id.fragment_container, detailFragment)
            } else {
                transaction.replace(R.id.fragment_container_overview, detailFragment)
            }
        }
    }

    override fun openMovieDetail(movie: Movie) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt(MovieDetailFragment.MOVIE_EXTRA, movie.id)
        var detailFragment = fragmentManager.findFragmentByTag(tagDetail)
        if (detailFragment != null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                transaction.replace(R.id.fragment_container, detailFragment)
            } else {
                transaction.replace(R.id.fragment_container_overview, detailFragment)
            }
        } else {
            detailFragment = createFragment(tagDetail) as MovieDetailFragment
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                transaction.replace(R.id.fragment_container, detailFragment).addToBackStack(null)
            } else {
                transaction.replace(R.id.fragment_container_overview, detailFragment)
                    .addToBackStack(null)
            }

        }

        detailFragment.arguments = bundle
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()

    }

    private fun replaceFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        var nowDisplayedFragment = fragmentManager.findFragmentByTag(tag)
        if (nowDisplayedFragment != null) {
            transaction.replace(R.id.fragment_container, nowDisplayedFragment)
        } else {
            nowDisplayedFragment = createFragment(tag)
            if (nowDisplayedFragment != null) {
                transaction.replace(R.id.fragment_container, nowDisplayedFragment, tag)
                    .addToBackStack(null)
            }
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun createFragment(tag: String): Fragment? {
        return when (tag) {
            tagDetail -> {
                MovieDetailFragment()
            }
            tagNavigation -> {
                NavigationFragment()
            }

            else -> null
        }

    }
}

