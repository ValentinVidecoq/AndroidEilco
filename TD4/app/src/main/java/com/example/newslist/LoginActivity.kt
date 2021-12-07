package com.example.newslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(getLocalClassName())

        val loginButton = findViewById<Button>(R.id.login)
        val loginLabel = findViewById<EditText>(R.id.loginLabel)
        loginButton.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            (applicationContext as NewsListApplication).login = loginLabel.text.toString()
            intent.putExtra("username", (applicationContext as NewsListApplication).login)
            startActivity(intent)
            finish()
        }
    }
}