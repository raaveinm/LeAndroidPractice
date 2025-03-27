package com.raaceinm.androidpracticals.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.raaceinm.androidpracticals.R

class BlankMenuFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = view.findViewById(R.id.drawer_layout)
        navigationView = view.findViewById(R.id.nav_drawer)

        val activityToolbar: Toolbar? = activity?.findViewById(R.id.toolbar)

        if (activityToolbar != null) {
            toggle = ActionBarDrawerToggle(
                requireActivity(),
                drawerLayout,
                activityToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            setHasOptionsMenu(true)

        } else {
            Log.e("BlankMenuFragment", "Activity toolbar is null")
        }

        navigationView.setNavigationItemSelectedListener(this)
        loadFragment(AnotherPrivate())
    }

    @SuppressLint("DiscouragedApi")
    private fun loadFragment(fragment: Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.blankContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.recyclerView -> {
                loadFragment(RecyclerViewFragment())
            }
            R.id.list_view -> {
                loadFragment(ListViewFragment())
            }
            R.id.another_private -> {
                loadFragment(AnotherPrivate())
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::toggle.isInitialized && toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        if (::toggle.isInitialized) {
            drawerLayout.removeDrawerListener(toggle)
        }
        super.onDestroyView()
    }
}