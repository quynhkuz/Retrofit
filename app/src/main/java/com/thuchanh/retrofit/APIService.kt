package com.thuchanh.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.Multipart
import retrofit2.http.POST




interface APIService
{


    @POST("demo1.json")
     fun getdata(): Call<User?>?


    //phai chua it nhat 1 phuong thuc gui di
    @Multipart
    @POST("demo1.json")
    fun update(
        @Field("ten") ten: String?,
        @Field("tuoi") tuoi: Int?
    ): Call<User?>?
}