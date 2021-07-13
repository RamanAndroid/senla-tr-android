package com.example.senlastudy.fragments.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.R
import com.example.senlastudy.databinding.FragmentNavigationBinding


class NavigationFragment : Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG_NOW_PLAYING = "MOVIE_NOW_PLAYING"
        private const val TAG_POPULAR = "MOVIE_POPULAR"
        private const val TAG_TOP_RATED = "MOVIE_TOP_RATED"
        private const val TAG_UP_COMING = "MOVIE_UPCOMING"
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
        replaceFragment(TAG_POPULAR)
        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_now_playing -> {
                        replaceFragment(TAG_NOW_PLAYING)
                    }
                    R.id.action_top_rated -> {
                        replaceFragment(TAG_TOP_RATED)
                    }
                    R.id.action_upcoming -> {
                        replaceFragment(TAG_UP_COMING)
                    }
                    R.id.action_popular -> {
                        replaceFragment(TAG_POPULAR)
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
                transaction.replace(R.id.fragment_container_navigation, nowDisplayedFragment, tag)
                    .addToBackStack(null)
            }
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun createFragment(tag: String): Fragment? {
        return when (tag) {
            TAG_NOW_PLAYING -> {
                MovieNowPlayingFragment()
            }
            TAG_POPULAR -> {
                MoviePopularListFragment()
            }
            TAG_UP_COMING -> {
                MovieUpcomingFragment()
            }
            TAG_TOP_RATED -> {
                MovieTopRatedFragment()
            }
            else -> null
        }
    }
}