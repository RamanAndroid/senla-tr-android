package com.example.senlastudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.adapter.MovieFragmentPagerAdapter
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.movie.MovieNowPlayingFragment
import com.example.senlastudy.fragments.movie.MoviePopularListFragment
import com.example.senlastudy.fragments.movie.MovieTopRatedFragment
import com.example.senlastudy.fragments.movie.MovieUpcomingFragment


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val adapter: MovieFragmentPagerAdapter by lazy {
        MovieFragmentPagerAdapter(
            supportFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(MoviePopularListFragment())

        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_now_playing -> {
                        replaceFragment(MovieNowPlayingFragment())
                    }
                    R.id.action_top_rated -> {
                        replaceFragment(MovieTopRatedFragment())
                    }
                    R.id.action_upcoming -> {
                        replaceFragment(MovieUpcomingFragment())
                    }
                    R.id.action_popular -> {
                        replaceFragment(MoviePopularListFragment())
                    }
                }
                true
            }
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }


}

