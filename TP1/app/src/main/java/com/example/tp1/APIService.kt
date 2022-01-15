package com.example.tp1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * In this service we send requests to the DMDB api
 */

interface APIService {
    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String): Call<MovieList?>?

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") api_key: String): Call<MovieList?>?

    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") api_key: String): Call<Movie?>?

    @GET("/3/search/movie")
    fun searchMovies(@Query("api_key") api_key: String, @Query("query") query: String): Call<MovieList?>?

    companion object{
        val ENDPOINT: String = "https://api.themoviedb.org/"
    }
}