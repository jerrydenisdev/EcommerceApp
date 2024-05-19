package com.example.appecommerce.MainModule.model

import com.example.appecommerce.Model.PremiereModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainInteractor {

    fun getStores(callBack: (MutableList<PremiereModel>) -> Unit ){
        doAsync {

            uiThread {

            }
        }
    }
}