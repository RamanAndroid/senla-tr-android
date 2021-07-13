package com.example.senlastudy

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.example.senlastudy.databinding.ActivityMainBinding
import com.example.senlastudy.fragments.movie.NavigationFragment
import com.example.senlastudy.fragments.movie.BaseMovieListFragment
import com.example.senlastudy.fragments.movie.MovieDetailFragment
import com.example.senlastudy.service.IObserver
import com.example.senlastudy.service.InternetStateService


class MainActivity : AppCompatActivity(), BaseMovieListFragment.Navigator {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG_DETAIL_FRAGMENT = "DETAIL_FRAGMENT"
        private const val TAG_NAVIGATION_FRAGMENT = "NAVIGATION_FRAGMENT"
        private const val TAG_MOVIE_ID = "TAG_MOVIE_ID"
    }

    private var movieId: Int = 0
    private var internetStateService: InternetStateService? = null
    private var isBound = false

    private val subscriberMainActivity = object : IObserver.InternetStateSubscriber {
        override fun update(internetState: Boolean) {
            changeInternetStateView(internetState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAG_MOVIE_ID, movieId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.btnOpenShareActivity.setOnClickListener {
            openShareActivity()
        }

        if (savedInstanceState != null) {
            movieId = savedInstanceState.getInt(TAG_MOVIE_ID)
            replaceDetailFragment(movieId)
        } else {
            replaceNavigationFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isBound) {
            val intent = Intent(this, InternetStateService::class.java)
            bindService(intent, connectBoundService, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onPause() {
        super.onPause()
        internetStateService?.getObserverStation()?.remove(subscriberMainActivity)
        if (isBound) {
            unbindService(connectBoundService)
        }
    }

    override fun openMovieDetailFragment(movieId: Int) {
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

    private val connectBoundService = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as InternetStateService.ServiceBinder
            internetStateService = binder.getService()
            internetStateService?.getObserverStation()?.register(subscriberMainActivity)
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            internetStateService?.getObserverStation()?.remove(subscriberMainActivity)
            isBound = false
            reconnectToBoundService()
        }
    }

    private fun changeInternetStateView(internetState: Boolean = false) {
        if (isBound) {
            binding.internetStateView.isSelected = true
            binding.internetStateView.isEnabled = internetState
        } else {
            binding.internetStateView.isSelected = false
        }
    }

    private fun reconnectToBoundService() {
        if (lifecycle.currentState >= Lifecycle.State.RESUMED) {
            val intent = Intent(this, InternetStateService::class.java)
            bindService(intent, connectBoundService, Context.BIND_AUTO_CREATE)
        }
    }

    private fun openShareActivity() {
        Toast.makeText(this, "Открывается меню с вашими контактами", Toast.LENGTH_LONG).show()
        val intent = Intent(this, ShareActivity::class.java)
        startActivity(intent)
    }

}
