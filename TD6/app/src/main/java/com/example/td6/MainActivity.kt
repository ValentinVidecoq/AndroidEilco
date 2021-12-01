package com.example.td6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var repo = "ValentinVidecoq"

        findViewById<Button>(R.id.searchButton).setOnClickListener {
            repo = findViewById<EditText>(R.id.searchEditText).text.toString()
            if (repo.isNotEmpty()) {
                var intent: Intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("repoName", repo.toString())
                startActivity(intent)
            }
        }

        val githubService = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        githubService.listRepos(repo)?.enqueue(object : Callback<List<Repo?>?> {
            override fun onResponse(call: Call<List<Repo?>?>?, response: Response<List<Repo?>?>) {
                response.body()?.let { afficherRepos(it) }
            }

            override fun onFailure(call: Call<List<Repo?>?>?, t: Throwable?) {}
        })


    }
    fun afficherRepos(repos: List<Repo?>) {
        Toast.makeText(this, "Nombre de dépots : " + repos.size, Toast.LENGTH_SHORT).show()
    }
}

