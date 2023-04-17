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

    init {
        ydao =ApiUtils.getYemeklerDao()
        yemeklerListesi = MutableLiveData()
        sepetListesi = MutableLiveData()

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
                Log.e("Kayıt","Sepete Eklendi")
                Log.e("Success",response.body()!!.success.toString())
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
            override fun onFailure(call: Call<YemekCevap>, t: Throwable) {
            }

            override fun onResponse(call: Call<YemekCevap>, response: Response<YemekCevap>) {
                val liste =response.body()!!.yemekler
                yemeklerListesi.value=liste
            }
        })
    }


    fun sepetGetir(kullanici_adi:String){
        var hashMap = HashMap<String,Sepet>()
        val other: List<Sepet> = emptyList()
    ydao.sepetGetir(kullanici_adi).enqueue(object : Callback<SepetCevap>{
        override fun onResponse(call: Call<SepetCevap>, response: Response<SepetCevap>) {
            try {

                val liste = response.body()?.sepet
                //Log.e("Liste",response.body()!!.sepet.toString())
                if (liste != null) {
                    for (i in liste){
                        if (hashMap.containsKey(i.yemek_adi)){
                            hashMap.get(i.yemek_adi)!!.yemek_siparis_adet += i.yemek_siparis_adet


                        }else{
                            hashMap.put(i.yemek_adi,i)
                        }

                    }
                }
                sepetListesi.value = hashMap.values.toList()
                //Log.e("tumsepet", "${hashMap.values.toList()}")
            }catch (e:Exception){
                Log.e("tumsepet",e.stackTrace.toString())
            }
        }

        override fun onFailure(call: Call<SepetCevap>, t: Throwable) {
            Log.e("Hata",t.toString())
        }

    })

    }



}