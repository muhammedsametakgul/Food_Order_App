package com.example.yemekuygulamasi.data.entitiy

import com.google.gson.annotations.SerializedName

data class SepetCevap(@SerializedName("sepet_yemekler") var sepet : List<Sepet>,
                      @SerializedName("success") var success : Int) {
}