package com.raaceinm.androidpracticals.activities

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.fragments.HomeFragment
import com.raaceinm.androidpracticals.fragments.DashboardFragment
import com.raaceinm.androidpracticals.fragments.BlankMenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Blank Menu"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.home)

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.home
            loadFragment(HomeFragment(), false)
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.home -> {
                    selectedFragment = HomeFragment()
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.home)
                    supportActionBar?.title = "Home"
                }
                R.id.dashboard -> {
                    selectedFragment = DashboardFragment()
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
                    supportActionBar?.title = "Dashboard"
                }
                R.id.blank_menu_fragment -> {
                    selectedFragment = BlankMenuFragment()
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
                    supportActionBar?.title = "Blank Menu"
                }
            }

            if (selectedFragment != null) {
                val addToBackStack = item.itemId != R.id.home
                loadFragment(selectedFragment, addToBackStack)
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                loadFragment(HomeFragment(), false)
                bottomNavigationView.selectedItemId = R.id.home
                supportActionBar?.setHomeAsUpIndicator(R.drawable.home)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.menuFragments, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}