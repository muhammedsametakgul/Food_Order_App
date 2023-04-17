package com.example.yemekuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.squareup.picasso.Picasso

class DetayViewmodel:ViewModel() {
    val yrepo =YemeklerRepository()
    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
      yrepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }


}