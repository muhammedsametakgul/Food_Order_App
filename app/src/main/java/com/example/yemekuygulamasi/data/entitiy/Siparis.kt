package com.example.yemekuygulamasi.data.entitiy

import com.google.firebase.database.DataSnapshot

data class Siparis(var siparis_id:String,var tarih: String, var sepetListe: List<Sepet>)
