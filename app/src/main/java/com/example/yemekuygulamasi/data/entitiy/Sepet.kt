package com.example.yemekuygulamasi.data.entitiy

import com.google.gson.annotations.SerializedName

data class Sepet(@SerializedName("sepet_yemek_id") var sepet_yemek_id:Int,
                 @SerializedName("yemek_adi") var yemek_adi:String,
                 @SerializedName("yemek_resim_adi") var yemek_resim_adi:String,
                 @SerializedName("yemek_fiyat") var yemek_fiyat:Int,
                 @SerializedName("yemek_siparis_adet") var yemek_siparis_adet:Int,
                 @SerializedName("kullanici_adi") var kullanici_adi:String):java.io.Serializable
