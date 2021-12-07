package com.example.tp1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface APIService {
    @GET("/movie/popular")
    fun getMovies(): Call<MovieList?>

    companion object{
        const val ENDPOINT = "https://api.themoviedb.org"
    }
}