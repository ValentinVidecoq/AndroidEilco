package com.example.tp1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcomingFragment : Fragment() {

    private var apiService: APIService = Retrofit.Builder()
        .baseUrl(APIService.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)
    private var movies: List<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Upcoming movies"

        // Call the service to get the upcoming movies
        apiService.getUpcomingMovies("c65dcc82ce82ef76b1b8afb960b54a35")?.enqueue(object :
            Callback<MovieList?> {
            override fun onResponse(call: Call<MovieList?>?, response: Response<MovieList?>) {

                // Store the api response as MovieList so we can use it
                movies = (response.body() as MovieList).results

                // Send data to the adapter so it can populate the recycler view
                var rvMovie : RecyclerView? = view?.findViewById(R.id.rvMoviesUpcoming)
                var mAdapter = MovieAdapter(movies){
                    var intent: Intent = Intent(activity, DetailsActivity::class.java)
                    intent.putExtra("id", it.id)
                    activity?.startActivity(intent)
                }
                rvMovie?.adapter = mAdapter
                rvMovie?.layoutManager = GridLayoutManager(container?.context, 2)
            }

            override fun onFailure(call: Call<MovieList?>?, t: Throwable?) {}
        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }
}