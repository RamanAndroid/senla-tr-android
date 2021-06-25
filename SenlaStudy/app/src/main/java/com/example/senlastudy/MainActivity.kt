package com.example.senlastudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.senlastudy.adapter.MovieFragmentPagerAdapter
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.movie.MovieNowPlayingFragment
import com.example.senlastudy.fragments.movie.MoviePopularListFragment
import com.example.senlastudy.fragments.movie.MovieTopRatedFragment
import com.example.senlastudy.fragments.movie.MovieUpcomingFragment


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val adapter:MovieFragmentPagerAdapter by lazy{MovieFragmentPagerAdapter(supportFragmentManager)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.addFragment(MoviePopularListFragment(),"Popular")
        adapter.addFragment(MovieNowPlayingFragment(),"Now playing")
        adapter.addFragment(MovieTopRatedFragment(),"Top rated")
        adapter.addFragment(MovieUpcomingFragment(),"Upcoming")

        binding.apply {
            pager.adapter= adapter
            tabs.setupWithViewPager(pager)
        }


    }


    //Детальный просмотр фильма
    //16 и 17

}

