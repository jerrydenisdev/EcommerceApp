package com.example.appecommerce

import android.app.Application
import com.example.appecommerce.Api.ApiService
import com.example.appecommerce.Api.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object{
        lateinit var retrofitCLient: RetrofitClient
        lateinit var currentUser: FirebaseUser

    }

    /* override fun onCreate() {
         super.onCreate()33815a528222a3d16e34881a4c0589c1

         val retrofit = Retrofit.Builder()
             .baseUrl("https://cp-staging.onrender.com/v1/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         apiService = retrofit.create(ApiService::class.java)

     }*/
}