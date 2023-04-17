package com.example.yemekuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

}