package com.example.yemekuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.squareup.picasso.Picasso

class DetayViewmodel:ViewModel() {
    val yrepo =YemeklerRepository()
    var sayiVM=MutableLiveData<String>()
    var sepetListesi = MutableLiveData<List<Sepet>>()

    init {
        sayiVM=yrepo.adetSayiGetir()
        sepetiYukle()
        sepetListesi=yrepo.sepetItemGetir()
    }
    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
      yrepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }
    fun sepetiYukle(){
        yrepo.sepetGetir("samet")
    }
    fun arttir(){
        yrepo.arttir()
    }
    fun azalt(){
        yrepo.azalt()
    }


    fun silDetay(sepet_yemek_id:Int,kullanici_adi:String){
        yrepo.sil(sepet_yemek_id,kullanici_adi)
    }


}