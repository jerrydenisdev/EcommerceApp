package com.example.appecommerce

import com.example.appecommerce.Model.CandyModel

/****
 * Project: Stores
 * From: com.cursosant.android.stores
 * Created by Alain Nicolás Tello on 26/11/20 at 13:24
 * Course: Android Practical with Kotlin from zero.
 * All rights reserved 2021.
 *
 * All my Courses(Only on Udemy):
 * https://www.udemy.com/user/alain-nicolas-tello/
 ***/
interface OnClickListener {
    fun onClick(storeId: Long)
    fun onFavoriteStore(candyEntity: CandyModel)
}