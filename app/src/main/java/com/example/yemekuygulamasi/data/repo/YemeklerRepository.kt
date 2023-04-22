package com.example.yemekuygulamasi.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemekuygulamasi.data.entitiy.*
import com.example.yemekuygulamasi.databinding.FragmentDetayBinding
import com.example.yemekuygulamasi.retrofit.ApiUtils
import com.example.yemekuygulamasi.retrofit.YemekDao
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerRepository {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var sepetListesi : MutableLiveData<List<Sepet>>
    var ydao:YemekDao
    private lateinit var bindingDetay:FragmentDetayBinding
    var sayiAdet=MutableLiveData<String>()

    init {
        ydao =ApiUtils.getYemeklerDao()
        yemeklerListesi = MutableLiveData()
        sepetListesi = MutableLiveData()
        sayiAdet=MutableLiveData("1")

    }


    fun yemekleriGetir() : MutableLiveData<List<Yemekler>>{
        return  yemeklerListesi
    }



    fun sepetItemGetir() : MutableLiveData<List<Sepet>>{
        return  sepetListesi
    }


    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        ydao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat, yemek_siparis_adet,kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

                    }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {
                Log.e("Kayıt","HATA")

            }

        })
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        ydao.sepettenYemekSil(sepet_yemek_id,kullanici_adi).enqueue(object  : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            sepetGetir(kullanici_adi)
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

            }

        })
    }


    fun yemekleriYukle(){
        ydao.tumYemekler().enqueue(object : Callback<YemekCevap>{
            override fun onResponse(call: Call<YemekCevap>, response: Response<YemekCevap>) {
                val liste =response.body()!!.yemekler
                yemeklerListesi.value=liste

            }
            override fun onFailure(call: Call<YemekCevap>, t: Throwable) {
            }


        })
    }


    fun sepetGetir(kullanici_adi:String){
         ydao.sepetGetir(kullanici_adi).enqueue(object : Callback<SepetCevap>{
        override fun onResponse(call: Call<SepetCevap>, response: Response<SepetCevap>) {
            val liste = response.body()!!.sepet
            sepetListesi.value = liste
        }

        override fun onFailure(call: Call<SepetCevap>, t: Throwable) {
            Log.e("Hata",t.toString())
        }

    })

    }

      fun arttir(){
        var sayi=sayiAdet.value.toString().toInt()
        sayi+=1
        sayiAdet.value=sayi.toString()
    }
    fun azalt(){
        var sayi=sayiAdet.value.toString().toInt()
        if(sayiAdet.value.toString().toInt() >1){
            sayi-=1
            sayiAdet.value=sayi.toString()
        }else{
            sayiAdet.value="1"
        }
    }
    fun adetSayiGetir():MutableLiveData<String>{
        return  sayiAdet
    }

    fun artanFiyat(){
        ydao.tumYemekler().enqueue(object :Callback<YemekCevap>{
            override fun onResponse(call: Call<YemekCevap>?, response: Response<YemekCevap>){
                val menu = response.body()!!.yemekler

                val artanFiyat = menu.sortedWith(compareBy { it.yemek_fiyat })
                yemeklerListesi.value = artanFiyat
            }
            override fun onFailure(call: Call<YemekCevap>?, t: Throwable?) {}
        })
    }

    fun azalanFiyat(){
        ydao.tumYemekler().enqueue(object :Callback<YemekCevap>{
            override fun onResponse(call: Call<YemekCevap>?, response: Response<YemekCevap>){
                val menu = response.body()!!.yemekler

                val azalanFiyat = menu.sortedWith(compareBy { it.yemek_fiyat }).reversed()
                yemeklerListesi.value = azalanFiyat
            }
            override fun onFailure(call: Call<YemekCevap>?, t: Throwable?) {}
        })
    }


}