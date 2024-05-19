package com.example.appecommerce.MainModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.appecommerce.Api.RetrofitClient
import com.example.appecommerce.Login
import com.example.appecommerce.MainModule.adapter.ImageAdapter
import com.example.appecommerce.MainModule.viewModel.MainViewModel
import com.example.appecommerce.Model.Premiere
import com.example.appecommerce.Model.PremiereModel
import com.example.appecommerce.MyApplication
import com.example.appecommerce.MyApplication.Companion.retrofitCLient
import com.example.appecommerce.R
import com.example.appecommerce.databinding.ActivityMainBinding
import kotlinx.coroutines.runBlocking
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //setUpTransformer()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

            val retrofitGet = RetrofitClient.apiService.getPrimieres()
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

       /* viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })*/
        // Kotlin

       // setupCaruoselView()


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
        /*carousel.carouselListener = object : CarouselListener{

            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater,
                parent: ViewGroup
            ): ViewBinding? {
                // ...
                val view = LayoutInflater.from(parent.context).inflate(R.layout.image_container, parent, false)
                return ViewBinding { view }
            }

            override fun onBindViewHolder(binding: ViewBinding, item: CarouselItem, position: Int) {
                var view = item.imageUrl!![position]


            }

            override fun onClick(position: Int, carouselItem: CarouselItem) {
                // ...
            }

            override fun onLongClick(position: Int, dataObject: CarouselItem) {
                // ...
            }

        }*/


    }
    /* private val runnable = Runnable {
         viewPager2.currentItem = viewPager2.currentItem + 1
     }

     private fun setUpTransformer(){
         val transformer = CompositePageTransformer()
         transformer.addTransformer(MarginPageTransformer(40))
         transformer.addTransformer { page, position ->
             val r = 1 - abs(position)
             page.scaleY = 0.85f + r * 0.14f
         }

         viewPager2.setPageTransformer(transformer)
     }

     override fun onPause() {
         super.onPause()

         handler.removeCallbacks(runnable)
     }

     override fun onResume() {
         super.onResume()

         handler.postDelayed(runnable , 2000)
     }*/

}