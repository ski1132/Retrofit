package com.example.test2


import retrofit2.Call
import retrofit2.http.GET


interface Service {
    @GET("/test/mockJson.php")
    fun getData(): Call<ResponseModel>
}
