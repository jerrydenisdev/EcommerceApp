package com.example.appecommerce.MainModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.appecommerce.Api.RetrofitClient
import com.example.appecommerce.Login
import com.example.appecommerce.Model.PremiereModel
import com.example.appecommerce.R
import com.example.appecommerce.databinding.ActivityMainBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMainBinding

    val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        loadGetPremieres()
    }
    fun loadGetPremieres(){
        doAsync {
            val retrofitGet = RetrofitClient.apiService.getPrimieres()
            uiThread {
                retrofitGet.enqueue(object : Callback<PremiereModel> {
                    override fun onResponse(call: Call<PremiereModel>, response: Response<PremiereModel>) {
                        if (response.isSuccessful){
                            response.body()?.premieres?.forEach {
                                setupCaruoselView(it.image)
                            }
                        }else{
                            Log.e("Error","Empty")
                        }
                    }

                    override fun onFailure(call: Call<PremiereModel>, t: Throwable) {
                        Log.e("Error","Empty")
                    }



                })
            }
        }
    }

    private fun setupCaruoselView(imageCarousel:String) {

        val carousel: ImageCarousel = findViewById(R.id.carousel)
        list.add(CarouselItem(imageCarousel))
        carousel.carouselListener = object : CarouselListener {
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                val intent = Intent(this@MainActivity,Login::class.java)
                startActivity(intent)

            }
        }
        carousel.setData(list)

    }

}