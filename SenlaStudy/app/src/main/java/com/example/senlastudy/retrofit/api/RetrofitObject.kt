package com.example.senlastudy.retrofit.api

import com.example.senlastudy.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {



    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .addConverterFactory(ConfigurationRetrofitObject.gsonConverter)
        .client(ConfigurationRetrofitObject.okHttpClient)
        .build()

    val apiService = retrofit.create(ApiMovie::class.java)
}