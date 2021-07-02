package com.example.senlastudy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.R
import com.example.senlastudy.databinding.FragmentNavigationBinding
import com.example.senlastudy.fragments.movie.MovieNowPlayingFragment
import com.example.senlastudy.fragments.movie.MoviePopularListFragment
import com.example.senlastudy.fragments.movie.MovieTopRatedFragment
import com.example.senlastudy.fragments.movie.MovieUpcomingFragment


class NavigationFragment : Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val tagNowPlaying = "MOVIE_NOW_PLAYING"
        private const val tagPopular = "MOVIE_POPULAR"
        private const val tagTopRated = "MOVIE_TOP_RATED"
        private const val tagUpcoming = "MOVIE_UPCOMING"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(tagPopular)
        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_now_playing -> {
                        replaceFragment(tagNowPlaying)
                    }
                    R.id.action_top_rated -> {
                        replaceFragment(tagTopRated)
                    }
                    R.id.action_upcoming -> {
                        replaceFragment(tagUpcoming)
                    }
                    R.id.action_popular -> {
                        replaceFragment(tagPopular)
                    }
                }
                true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun replaceFragment(tag: String) {
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        var nowDisplayedFragment = fragmentManager.findFragmentByTag(tag)
        if (nowDisplayedFragment != null) {
            transaction.replace(R.id.fragment_container_navigation, nowDisplayedFragment)
        } else {
            nowDisplayedFragment = createFragment(tag)
            if (nowDisplayedFragment != null) {
                transaction.add(R.id.fragment_container_navigation, nowDisplayedFragment, tag)
                    .addToBackStack(null)
            }
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun createFragment(tag: String): Fragment? {
        return when (tag) {
            tagNowPlaying -> {
                MovieNowPlayingFragment()
            }
            tagPopular -> {
                MoviePopularListFragment()
            }
            tagUpcoming -> {
                MovieUpcomingFragment()
            }
            tagTopRated -> {
                MovieTopRatedFragment()
            }
            else -> null
        }

    }
}