package com.example.tp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * In this activity we display the details of a movie after the user clicked on its poster
 */

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra("id", 0)
        val apiService: APIService = Retrofit.Builder()
            .baseUrl(APIService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
        var movie: Movie
        val tvTagline: TextView = findViewById(R.id.tvTagline)
        val ivBackdrop: ImageView = findViewById(R.id.ivBackdrop)
        val tvOverview: TextView = findViewById(R.id.tvOverview)
        val tvReleaseDate: TextView = findViewById(R.id.tvReleaseDate)
        val tvRating: TextView = findViewById(R.id.tvRating)

        // Call the service to get the details of the movie
        apiService.getMovieDetails(id,"c65dcc82ce82ef76b1b8afb960b54a35")?.enqueue(object :
            Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>?, response: Response<Movie?>) {

                // Store the api response as Movie so we can use it
                movie = (response.body() as Movie)

                // Populate the layout with the data and handle empty values
                title = movie.title

                if (movie.tagline == ""){
                    tvTagline.visibility = View.GONE
                } else{
                    tvTagline.text = movie.tagline
                }

                if (movie.backdrop_path == null) {
                    Glide.with(ivBackdrop).load("https://image.tmdb.org/t/p/original/" + movie.poster_path).into(ivBackdrop)
                } else {
                    Glide.with(ivBackdrop).load("https://image.tmdb.org/t/p/original/" + movie.backdrop_path).into(ivBackdrop)
                }

                if (movie.overview == ""){
                    tvOverview.visibility = View.GONE
                } else{
                    tvOverview.text = movie.overview
                }

                tvReleaseDate.text = "Release date : " + movie.release_date

                if (movie.vote_count == 0) {
                    tvRating.text = "No rating\n"
                } else{
                    tvRating.text = "Rating : " + movie.vote_average + " (" + movie.vote_count + " votes)\n"
                }
            }
            override fun onFailure(call: Call<Movie?>?, t: Throwable?) {}
        })

    }
}