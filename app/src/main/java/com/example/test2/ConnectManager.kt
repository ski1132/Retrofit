package com.example.test2

import okhttp3.Callback
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody




class NetworkConnectionManager {
    fun callServer(listener: OnNetworkCallbackListener, username: String) {
        val retrofit =Retrofit.Builder().baseUrl("http://rakgun.com/test/mockJson.php")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val git = retrofit.create(GitHubService::class.java)
        val call = git.getUser(username)
        call.enqueue(object : Callback<Model>() {
//            fun onResponse(response: Response<User>, retrofit: Retrofit) {}
//            fun onFailure(t: Throwable) {}
        })
    }
}
interface OnNetworkCallbackListener {
    fun onResponse(user: Model, retrofit: Retrofit)
    fun onBodyError(responseBodyError: ResponseBody)
    fun onBodyErrorIsNull()
    fun onFailure(t: Throwable)
}