package com.example.td6

//import com.google.firebase.database.core.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit




interface GithubService {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo?>?>?

    @GET("/search/repositories")
    fun searchRepos(@Query("q") query: String): Call<RepoList?>

    companion object{
        const val ENDPOINT = "https://api.github.com"
    }
}
