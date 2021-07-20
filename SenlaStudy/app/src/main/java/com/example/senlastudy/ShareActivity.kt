package com.example.senlastudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.databinding.ActivityShareBinding
import com.example.senlastudy.fragments.movie.MoviePopularListFragment
import com.example.senlastudy.fragments.share.ContactFragment

class ShareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareBinding
    private var movieTitle: String = ""
    private var movieVoteCount: String = ""
    private var movieVoteAverage: String = ""

    companion object {
        private const val TAG_CONTACT = "CONTACT"
        private const val TAG_SHARE = "SHARE"
        const val MOVIE_EXTRA_TITLE = "MOVIE_EXTRA_TITLE"
        const val MOVIE_EXTRA_VOTE_COUNT = "MOVIE_EXTRA_VOTE_COUNT"
        const val MOVIE_EXTRA_VOTE_AVERAGE = "MOVIE_EXTRA_VOTE_AVERAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        movieTitle =
            intent?.getStringExtra(MOVIE_EXTRA_TITLE) ?: error("cannot find movie title")
        movieVoteCount =
            intent?.getStringExtra(MOVIE_EXTRA_VOTE_COUNT) ?: error("cannot find movie vote count")
        movieVoteAverage =
            intent?.getStringExtra(MOVIE_EXTRA_VOTE_AVERAGE)
                ?: error("cannot find movie vote average")
        replaceFragment(TAG_CONTACT)
    }

    private fun replaceFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString(MOVIE_EXTRA_TITLE, movieTitle)
        bundle.putString(MOVIE_EXTRA_VOTE_COUNT, movieVoteCount)
        bundle.putString(MOVIE_EXTRA_VOTE_AVERAGE, movieVoteAverage)
        var nowDisplayedFragment = fragmentManager.findFragmentByTag(tag)
        if (nowDisplayedFragment != null) {
            transaction.replace(R.id.fragment_container, nowDisplayedFragment)
        } else {
            nowDisplayedFragment = createFragment(tag)
            transaction.replace(R.id.fragment_container, nowDisplayedFragment, tag)
                .addToBackStack(null)
        }
        nowDisplayedFragment.arguments = bundle
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun createFragment(tag: String): Fragment {
        return when (tag) {
            TAG_CONTACT -> {
                ContactFragment()
            }
            TAG_SHARE -> {
                MoviePopularListFragment()
            }
            else -> error("Unexpected tag $tag")
        }
    }

}