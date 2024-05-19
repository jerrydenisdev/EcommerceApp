package com.example.appecommerce.MainModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appecommerce.Model.Premiere
import com.example.appecommerce.MyApplication
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {


    init {
       // setupGetMovie()
    }

    fun setupGetMovie(){

      /*  viewModelScope.launch {
            val servisApi = MyApplication()
            val retrofit = servisApi.apiService.getPrimieres()
            retrofit.enqueue(object : Callback<Premiere>{
                override fun onResponse(call: Call<Premiere>, response: Response<Premiere>) {

                }

                override fun onFailure(call: Call<Premiere>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }*/

    }


}