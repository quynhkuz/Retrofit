package com.thuchanh.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitAPI {


    var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()

    var apiService : APIService = Retrofit.Builder()
        .baseUrl("https://khoapham.vn/KhoaPhamTraining/json/tien/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(APIService::class.java)

}