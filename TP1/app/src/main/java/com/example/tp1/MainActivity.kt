package com.example.tp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * This activity hosts the bottom navigation bar which we use to navigate through the fragments
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)

        // Open the Popular Fragment when the app starts
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_layout, PopularFragment())
            .commit()

        // Then listen to clicks on the navigation bar to change the fragment displayed
        bottomNav.setOnItemSelectedListener {
            var selectedFragment: Fragment = PopularFragment()
            when (it.itemId) {
                R.id.action_popular -> selectedFragment = PopularFragment()
                R.id.action_upcoming -> selectedFragment = UpcomingFragment()
                R.id.action_search -> selectedFragment = SearchFragment()
            }

            // Replace the current fragment with the new fragment
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_layout, selectedFragment)
                .commit()
            return@setOnItemSelectedListener true
        }
    }
}