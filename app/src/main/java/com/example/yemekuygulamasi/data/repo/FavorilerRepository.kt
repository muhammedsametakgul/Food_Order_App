package com.example.yemekuygulamasi.data.repo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.yemekuygulamasi.data.entitiy.Favoriler
import com.example.yemekuygulamasi.room.FavorilerDao
import com.example.yemekuygulamasi.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavorilerRepository(var application: Application) {
    var favoriListesi:MutableLiveData<List<Favoriler>>
    val vt:Veritabani
    init {
        favoriListesi = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application)!!
    }

    fun favoriGetir(): MutableLiveData<List<Favoriler>>{
        return favoriListesi
    }

    fun favoriAl(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            favoriListesi.value = vt.favorilerDao().tumFavoriler()

        }

    }

    fun favoriEkle(yemek_id:Int,yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
        val yeniFavori=Favoriler(0,yemek_adi,yemek_resim_adi,yemek_fiyat)
        vt.favorilerDao().favoriEkle(yeniFavori)
        }

    }

    fun favoriSil(yemek_id:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniFavori=Favoriler(yemek_id,"","",0)
            vt.favorilerDao().favoriSil(yeniFavori)
            favoriAl()
        }
    }
    fun favoriHepsiniSil(){
        val liste = favoriListesi.value

           for(i in 0..liste!!.size-1){
               favoriSil(liste.get(i).yemek_id)
           }
        favoriAl()

    }
}