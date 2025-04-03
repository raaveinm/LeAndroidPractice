package com.raaceinm.androidpracticals.activities

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.fragments.HomeFragment
import com.raaceinm.androidpracticals.fragments.DashboardFragment
import com.raaceinm.androidpracticals.fragments.BlankMenuFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

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

    override fun onStart() {
        super.onStart()
        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        alertBuilder
                .setTitle("alert sample")
                .setIcon(R.drawable.home)
                .setMessage("this is an alert message")
                .setPositiveButton("positive") { dialog, which -> dialog.dismiss() }
                .setNegativeButton("negative") { dialog, which -> dialog.cancel() }
                .setNeutralButton("neutral") { dialog, which ->
                    CoroutineScope(kotlinx.coroutines.Dispatchers.Main)
                        .launch { customResolve() } }
                .create()
                .show()

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

private suspend fun MainActivity.customResolve() {
    Snackbar.make(findViewById(android.R.id.content),
        "it is over", Snackbar.LENGTH_SHORT).show()

    channelFlow<Unit> {
        launch {
            delay(10000)
            send(Unit)
        }
    }.collect { startActivity(Intent(
        this@customResolve, TimePicker::class.java)
    ) }

}
