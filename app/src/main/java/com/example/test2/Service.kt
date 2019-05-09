package com.example.test2

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<User>
}
