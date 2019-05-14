package com.example.test2


import retrofit2.Call
import retrofit2.http.*


interface UserServive {
    @FormUrlEncoded
    @POST("/test/login.php")
    fun getToken(@Field("username") username:String ,
                 @Field("password") password:String)
            : Call<LoginModel>
}
