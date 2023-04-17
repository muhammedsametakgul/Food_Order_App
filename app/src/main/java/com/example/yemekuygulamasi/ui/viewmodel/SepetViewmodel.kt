package com.example.yemekuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.data.repo.YemeklerRepository

class SepetViewmodel:ViewModel() {
    //phuc@gmail.com
    val user="samet"
    val yrepo=YemeklerRepository()
    var sepetler: MutableLiveData<List<Sepet>>

    init {
        sepetiGetir()
        sepetler = yrepo.sepetItemGetir()

    }
    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
       yrepo.sil(sepet_yemek_id,kullanici_adi)
        if (sepetler.value!!.size -1 == 0){
            sepetler.value = emptyList()
        }
    }
    fun sepetiGetir(){
    yrepo.sepetGetir(user)
    }
}