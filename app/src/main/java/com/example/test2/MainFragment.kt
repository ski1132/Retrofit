package com.example.test2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast as Toast1
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("http://rakgun.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(Service::class.java)

        val call = service.getData()

        call.enqueue( object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>?, t: Throwable?) {
                Log.e("---- On faulure --- = "," can't")
            }

            override fun onResponse(call: Call<ResponseModel>?, response: Response<ResponseModel>) {
                val dataModel : ResponseModel = response.body()

                if (response.code() == 200)
                {
                    Log.e("---- dataModel --- = ",dataModel.person[0].firstName.toString())
                    Log.e("---- call --- = ", call.toString())

                    activity?.also {
                        val arrayData: ArrayList<PersonModel> = arrayListOf()
                        for(i in 0..2) {
                            arrayData.add(
                                PersonModel(
                                    firstName = dataModel.person[i].firstName.toString(),
                                    lastName = dataModel.person[i].lastName.toString(),
                                    age = dataModel.person[i].age.toString(),
                                    picture = dataModel.person[i].picture.toString()
                                )
                            )
                        }
                        recycleView?.layoutManager = LinearLayoutManager(it)
                        recycleView.adapter = MainAdapter(arrayData, it)
                    }
                }
                else {
                    response.errorBody()
                }



            }

        })

    }



}
