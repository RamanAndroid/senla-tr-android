package com.example.senlastudy


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.senlastudy.adapter.MovieFragmentPagerAdapter
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.NavigationFragment


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

        if (savedInstanceState == null) {
            val f1 = NavigationFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment_container, f1)
            fragmentTransaction.commit()
        }


    }


}

