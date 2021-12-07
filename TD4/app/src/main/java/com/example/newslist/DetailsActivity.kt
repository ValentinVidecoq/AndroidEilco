package com.example.newslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTitle(getLocalClassName())

        val okButton = findViewById<Button>(R.id.ok)
        val loginLabel = findViewById<TextView>(R.id.loginLabel)
        loginLabel.text = "Bonjour, " + (applicationContext as NewsListApplication).login

        okButton.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            intent.putExtra("username", (applicationContext as NewsListApplication).login)
            startActivity(intent)
        }
    }
}