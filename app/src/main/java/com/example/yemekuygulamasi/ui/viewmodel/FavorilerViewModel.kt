package com.example.yemekuygulamasi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.entitiy.Favoriler
import com.example.yemekuygulamasi.data.repo.FavorilerRepository


class FavorilerViewModel(application: Application)  : AndroidViewModel(application){
    var favorilerListesi = MutableLiveData<List<Favoriler>>()
    val frepo = FavorilerRepository(application)
    init {
        favoriYukle()
        favorilerListesi = frepo.favoriGetir()

    }

    fun favoriYukle(){
        frepo.favoriAl()
    }


    fun favoriEkle(yemek_id:Int,yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int){
        frepo.favoriEkle(yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat)
    }

    fun favoriSil(yemek_id:Int){
      frepo.favoriSil(yemek_id)
    }
    fun favoriHepsiniSil(){
        frepo.favoriHepsiniSil()
    }

}