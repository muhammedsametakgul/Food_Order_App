package com.example.yemekuygulamasi.ui.viewmodel

import android.util.Log
import android.widget.Toast
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

    fun sepetiBosalt(){
      if(sepetler.value != null){
          val tumYemek = sepetler.value!!
          for (i in tumYemek){
              sepettenSil(i.sepet_yemek_id,user)
              if (tumYemek.indexOf(i) == tumYemek.size-1){
                  sepetler.value = emptyList()
              }
              if (sepetler.value!!.size -1 == 0){
                  sepetler.value = emptyList()
              }
          }
      }
        yrepo.sepetGetir(user)

    }
    fun sepettenSil(sepet_yemek_id:Int,kullanici_adi:String){
        yrepo.sil(sepet_yemek_id,kullanici_adi)
        if (sepetler.value!!.size -1 == 0){
            sepetler.value = emptyList()
        }

    }

}