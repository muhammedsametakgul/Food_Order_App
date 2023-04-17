package com.example.yemekuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.squareup.picasso.Picasso

class DetayViewmodel:ViewModel() {
    val yrepo =YemeklerRepository()
    var sayiVM=MutableLiveData<String>()
    init {
        sayiVM=MutableLiveData("1")
    }
    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
      yrepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }
    fun arttir(){
        var sayi=sayiVM.value.toString().toInt()
        sayi+=1
        sayiVM.value=sayi.toString()
    }
    fun azalt(){
        var sayi=sayiVM.value.toString().toInt()
        if(sayiVM.value.toString().toInt() >1){
            sayi-=1
            sayiVM.value=sayi.toString()
        }else{
            sayiVM.value="1"
        }
    }



}