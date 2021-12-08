package com.example.tp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)

        supportFragmentManager.beginTransaction().replace(R.id.main_layout, PopularFragment()).commit()

        bottomNav.setOnItemSelectedListener {
            var selectedFragment: Fragment = PopularFragment()
            when (it.itemId) {
                R.id.action_popular -> selectedFragment = PopularFragment()
                R.id.action_upcoming -> selectedFragment = UpcomingFragment()
                R.id.action_search -> selectedFragment = SearchFragment()
            }
            // It will help to replace the one fragment to other.
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_layout, selectedFragment)
                .commit()
            return@setOnItemSelectedListener true
        }

    }
}