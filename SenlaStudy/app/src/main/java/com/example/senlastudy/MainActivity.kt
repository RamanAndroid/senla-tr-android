package com.example.senlastudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.senlastudy.adapter.MovieFragmentPagerAdapter
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.MovieNowPlayingFragment
import com.example.senlastudy.fragments.MoviePopularFragment
import com.example.senlastudy.fragments.MovieTopRatedFragment
import com.example.senlastudy.fragments.MovieUpcomingFragment


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val adapter:MovieFragmentPagerAdapter by lazy{MovieFragmentPagerAdapter(supportFragmentManager)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter.addFragment(MovieNowPlayingFragment(),"Now playing")
        adapter.addFragment(MoviePopularFragment(),"Popular")
        adapter.addFragment(MovieTopRatedFragment(),"Top rated")
        adapter.addFragment(MovieUpcomingFragment(),"Upcoming")

        binding.apply {
            pager.adapter= adapter
            tabs.setupWithViewPager(pager)
        }


    }

}

