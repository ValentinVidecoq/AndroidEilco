package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {
    private var apiService: APIService = Retrofit.Builder()
        .baseUrl(APIService.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)
    private var movies: List<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_search, container, false)
        activity?.title = "Search"

        // Inflate the view now so we can use setOnClickListener
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)

        // Listen to the search button so we can trigger the search when it's clicked
        view.findViewById<Button>(R.id.searchButton)?.setOnClickListener{
            val searchText = view.findViewById<EditText>(R.id.searchEditText)?.text

            // Don't trigger the search if the EditText is empty
            if (searchText.toString() == "") {
                return@setOnClickListener
            }

            activity?.title = "Results for : " + searchText.toString()

            // Call the service to get the search results
            apiService.searchMovies("c65dcc82ce82ef76b1b8afb960b54a35", searchText.toString())?.enqueue(object :
                Callback<MovieList?> {
                override fun onResponse(call: Call<MovieList?>?, response: Response<MovieList?>) {

                    // Set visibility of the EditText and Button to GONE in order to make room for the recycler view containing the results of the search
                    view.findViewById<EditText>(R.id.searchEditText)?.visibility = View.GONE
                    view.findViewById<Button>(R.id.searchButton)?.visibility = View.GONE

                    // Store the api response as MovieList so we can use it
                    movies = (response.body() as MovieList).results

                    // Send data to the adapter so it can populate the recycler view
                    val rvMovie : RecyclerView? = view.findViewById(R.id.rvMoviesSearch)
                    val mAdapter = MovieAdapter(movies){
                        val intent: Intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra("id", it.id)
                        activity?.startActivity(intent)
                    }
                    rvMovie?.adapter = mAdapter
                    rvMovie?.layoutManager = GridLayoutManager(container?.context, 2)
                }

                override fun onFailure(call: Call<MovieList?>?, t: Throwable?) {}
            })
        }

        // Inflate the layout for this fragment
        return view
    }
}