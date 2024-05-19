package com.example.appecommerce.Api

import com.example.appecommerce.Model.Premiere
import com.example.appecommerce.Model.PremiereModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("premieres")
    fun getPrimieres():Call<PremiereModel>

}