package com.example.yemekuygulamasi.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.example.yemekuygulamasi.ui.BadgeBox

class AnasayfaViewmodel:ViewModel() {
    val yrepo=YemeklerRepository()
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var yemekFiltre: MutableLiveData<List<Yemekler>>

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
        yemekFiltre = MutableLiveData()

    }

    fun yemekleriYukle(){
        yrepo.yemekleriYukle()
    }

    fun ara(aramaKelimesi:String) {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
        if(aramaKelimesi.isBlank()){
            return
        }

        val items = yemeklerListesi.value
        val filteredMeals = items!!.filter {
            it.yemek_adi.contains(aramaKelimesi, ignoreCase = true)
        }
        Log.e("items",filteredMeals.toString())
        yemeklerListesi = MutableLiveData(filteredMeals)

    }

        fun artanFiyat() {
            yrepo.artanFiyat()
        }

        fun azalanFiyat() {
            yrepo.azalanFiyat()
        }


    }