package com.example.appecommerce.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://cp-staging.onrender.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

     val apiService = retrofit.create(ApiService::class.java)
}