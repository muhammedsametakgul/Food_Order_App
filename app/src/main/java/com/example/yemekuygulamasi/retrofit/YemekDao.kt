package com.example.yemekuygulamasi.retrofit

import com.example.yemekuygulamasi.data.entitiy.CRUDCevap
import com.example.yemekuygulamasi.data.entitiy.SepetCevap
import com.example.yemekuygulamasi.data.entitiy.YemekCevap
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemekDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/
    //yemekler/tumYemekleriGetir.php
    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemekCevap>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteEkle(@Field("yemek_adi")  yemek_adi:String,
                   @Field("yemek_resim_adi") yemek_resim_adi:String,
                   @Field("yemek_fiyat") yemek_fiyat :Int,
                   @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                   @Field("kullanici_adi") kullanici_adi:String
                   ) : Call<CRUDCevap>


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetGetir(@Field("kullanici_adi") kullanici_adi: String) : Call<SepetCevap>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenYemekSil(@Field("sepet_yemek_id") yemek_adi: Int,
                         @Field("kullanici_adi") kullanici_adi: String):Call<CRUDCevap>

}