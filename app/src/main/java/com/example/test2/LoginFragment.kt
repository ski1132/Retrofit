package com.example.test2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated (view: View, savedInstanceState: Bundle?){
        btSend.setOnClickListener()
        {
            Log.e("---- user --- = ",user.text.toString())
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().baseUrl("http://rakgun.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(UserServive::class.java)
            val call = service.getToken(user.text.toString(),pass.text.toString())

            call.enqueue( object : Callback<LoginModel> {
                override fun onFailure(call: Call<LoginModel>?, t: Throwable?) {
                    Log.e("---- On faulure --- = "," can't")
                }

                override fun onResponse(call: Call<LoginModel>?, response: Response<LoginModel>) {


                    if (response.code() == 200)
                    {

                        val logModel : LoginModel = response.body()

                        logModel.token?.let{
                            Log.e("---- dataModel --- = ",logModel.status)
                            Log.e("---- call --- = ", call.toString())

                            Toast.makeText(context, "Correct Username and Password", Toast.LENGTH_SHORT).show()
                            activity?.also {activity->
                                activity.supportFragmentManager.beginTransaction().replace(R.id.mainFrag, MainFragment.sentToken(it))
                                    .addToBackStack(null).commit()
                            }
                        }?:run{
                            Toast.makeText(activity,logModel.message,Toast.LENGTH_SHORT).show()
                        }

                    }
                    else {
                        response.errorBody()
                    }
                }

            })
        }
    }


}// Required empty public constructor
