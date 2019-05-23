package com.example.test2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast as Toast1
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    companion object {
        fun sentToken(token: String): MainFragment {
            val bundle = Bundle()
            bundle.putString("KEY_TOKEN", token)
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

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
        val service = retrofit.create(DataService::class.java)

        val call = service.getData(arguments!!.getString("KEY_TOKEN"))

        call.enqueue( object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseModel>?, response: Response<ResponseModel>) {


                if (response.code() == 200)
                {
                    val logModel : ResponseModel = response.body()!!
                    activity?.also {
                        viewPagerUI.adapter = CustomPagerAdapter(logModel.person,it)
                        indicator.setViewPager(viewPagerUI)
                        //recycleView.adapter = MainAdapter(logModel.person, it)
                    }
                }
                else {
                    response.errorBody()
                }
            }

        })

    }



}
