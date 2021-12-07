package com.example.newslist

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setTitle(getLocalClassName())

        val detailsButton = findViewById<Button>(R.id.details)
        val aboutButton = findViewById<Button>(R.id.about)
        val logoutButton = findViewById<Button>(R.id.logout)
        val loginLabel = findViewById<TextView>(R.id.usernameLabel)

        if(intent.hasExtra("username")) {
            val username = intent.getStringExtra("username")
            loginLabel.text = "Bonjour, " + username
        }

        detailsButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
        aboutButton.setOnClickListener {
            val url = "https://news.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}