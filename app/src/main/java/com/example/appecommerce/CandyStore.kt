package com.example.appecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appecommerce.Api.RetrofitClient
import com.example.appecommerce.Model.CandyModel
import com.example.appecommerce.databinding.ActivityCandyStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CandyStore : AppCompatActivity(),OnClickListener {

    private lateinit var mbinding:ActivityCandyStoreBinding

    var result: String? = null
    private lateinit var mAdapter:CandyAdapter
    private lateinit var mGridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityCandyStoreBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        val extras = intent.extras
        extras?.let {
            result = it.getString("Key")
        }
        setupRecyclerView()
    }

    fun setupRecyclerView(){
        mAdapter = CandyAdapter(mutableListOf(),this)
        mGridLayoutManager = GridLayoutManager(this, 1)
        getStore()

        mbinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayoutManager
            adapter = mAdapter
        }
    }

    private fun getStore() {
        val retrofitGet = RetrofitClient.apiService.getCandyStore()
        retrofitGet.enqueue(object : Callback<CandyModel>{
            override fun onResponse(call: Call<CandyModel>, response: Response<CandyModel>) {
                if (response.isSuccessful){
                   val item =  response.body()?.items
                    if (item != null) {
                        mAdapter.setStores(item)
                    }
                }else{
                    Log.e("E","Error Empty")
                }
            }

            override fun onFailure(call: Call<CandyModel>, t: Throwable) {
                Log.e("E","Error Empty")

            }

        })
    }

    override fun onClick(storeId: Long) {
        TODO("Not yet implemented")
    }

    override fun onFavoriteStore(candyEntity: CandyModel) {
        TODO("Not yet implemented")
    }



}