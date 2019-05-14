package com.example.test2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {
    @GET("/test/persons.php")
    fun getData(@Query("token") token:String): Call<ResponseModel>
}

