package com.example.tp1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*class PopularActivity: AppCompatActivity {

    val apiService = Retrofit.Builder()
        .baseUrl(APIService.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)

    var movies: List<Movie> = ArrayList<Movie>()

    apiService.getMovies()?.enqueue(object : Callback<MovieList?> {
        override fun onResponse(call: Call<MovieList?>, response: Response<MovieList?>) {
            movies = (response.body() as MovieList).items
            recycler.adapter = SearchAdapter(reposInRepo) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url)))
            }
            recycler.layoutManager = LinearLayoutManager(this@SearchActivity)
        }

        override fun onFailure(call: Call<RepoList?>, t: Throwable) {}
    })
}*/