package com.example.appecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CandyStore : AppCompatActivity() {

    var result: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candy_store)

        val extras = intent.extras
        extras?.let {
            result = it.getString("Key")
        }
    }
}