package com.example.test2

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("?fbclid=IwAR1wtEthKHdwm0ZMxDCvN2jmA2IeuVYk4FtDmHgSuVIwa9jHQhEpG3wpJFI")
    fun getUser(@Path("username") username: String): Call<Model>
}
