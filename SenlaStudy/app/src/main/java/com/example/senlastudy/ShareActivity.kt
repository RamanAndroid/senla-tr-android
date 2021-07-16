package com.example.senlastudy

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.senlastudy.database.entity.Contact
import com.example.senlastudy.databinding.ActivityShareBinding
import com.example.senlastudy.fragments.movie.MoviePopularListFragment
import com.example.senlastudy.fragments.share.ContactFragment

class ShareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareBinding

    companion object {
        private const val TAG_CONTACT = "CONTACT"
        private const val TAG_SHARE = "SHARE"
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
        replaceFragment(TAG_CONTACT)
    }

    private fun replaceFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        var nowDisplayedFragment = fragmentManager.findFragmentByTag(tag)
        if (nowDisplayedFragment != null) {
            transaction.replace(R.id.fragment_container, nowDisplayedFragment)
        } else {
            nowDisplayedFragment = createFragment(tag)
            transaction.replace(R.id.fragment_container, nowDisplayedFragment, tag)
                .addToBackStack(null)
        }
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