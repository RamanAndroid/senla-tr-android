package com.example.regexlessonsixteen

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import com.example.regexlessonsixteen.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val drawerMenuAdapter by lazy {
        DrawerMenuAdapter(layoutInflater, drawerMenuList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupToolbar()
        replaceMenuFragment(0)

        binding.apply {
            navItemList.apply {
                adapter = drawerMenuAdapter
                navItemList.setOnItemClickListener { _, _, position, _ ->
                    replaceMenuFragment(position)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }


        }

    }

    private fun replaceMenuFragment(position: Int) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when (position) {
            0 -> transaction.replace(R.id.fragment_container, RegexFragment())
            1 -> transaction.replace(R.id.fragment_container, SettingsFragment())
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun drawerMenuList(): List<DrawerItem> {
        return listOf(
            DrawerItem(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_home, null),
                "Преобразователь"
            ),
            DrawerItem(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_setting, null),
                "Настройки"
            )
        )
    }

    private fun setupToolbar() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            val drawerToggle = ActionBarDrawerToggle(
                this@MainActivity,
                binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerToggle.syncState()
            drawerLayout.addDrawerListener(drawerToggle)
        }
    }


}