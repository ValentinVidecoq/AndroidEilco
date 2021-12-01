package com.example.td6

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_repos)

        val repo = intent.getStringExtra("repoName").toString()

        val githubService = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        var recycler: RecyclerView = findViewById(R.id.searchResults)
        var reposInRepo: List<Repo> = ArrayList<Repo>()

        githubService.searchRepos(repo)?.enqueue(object : Callback<RepoList?> {
            override fun onResponse(call: Call<RepoList?>, response: Response<RepoList?>) {
                reposInRepo = (response.body() as RepoList).items
                recycler.adapter = SearchAdapter(reposInRepo) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url)))
                }
                recycler.layoutManager = LinearLayoutManager(this@SearchActivity)
            }

            override fun onFailure(call: Call<RepoList?>, t: Throwable) {}
        })
    }
}
