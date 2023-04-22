package com.example.yemekuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.data.repo.YemeklerRepository

class AnasayfaViewmodel:ViewModel() {
    val yrepo=YemeklerRepository()
    var yemeklerListesi: MutableLiveData<List<Yemekler>>

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()


    }

    fun yemekleriYukle(){
        yrepo.yemekleriYukle()
    }

    fun ara(aramaKelimesi:String){
        if (aramaKelimesi.length == 0){
            yrepo.yemekleriYukle()
        }else{
            yemeklerListesi.value = yemeklerListesi.value!!.filter { yemekler ->
                yemekler.yemek_adi.lowercase().contains(aramaKelimesi.lowercase()) }

        }

    }

    fun artanFiyat(){
        yrepo.artanFiyat()
    }

    fun azalanFiyat(){
        yrepo.azalanFiyat()
    }

}