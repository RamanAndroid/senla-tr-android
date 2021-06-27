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

    private val movieNowPlayingFragment = MovieNowPlayingFragment()
    private val movieTopRatedFragment = MovieTopRatedFragment()
    private val movieUpcomingFragment = MovieUpcomingFragment()
    private val moviePopularListFragment = MoviePopularListFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(moviePopularListFragment)

        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_now_playing -> {
                        replaceFragment(movieNowPlayingFragment)
                    }
                    R.id.action_top_rated -> {
                        replaceFragment(movieTopRatedFragment)
                    }
                    R.id.action_upcoming -> {
                        replaceFragment(movieUpcomingFragment)
                    }
                    R.id.action_popular -> {
                        replaceFragment(moviePopularListFragment)
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


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.let {
            transaction.replace(R.id.fragment_container_navigation, fragment)
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }

    }


}