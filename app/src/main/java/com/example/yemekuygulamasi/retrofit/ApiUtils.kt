package com.example.yemekuygulamasi.retrofit

import retrofit2.create

class ApiUtils {
    companion object{
        val BASE_URL="http://kasimadalan.pe.hu/"

        fun getYemeklerDao():YemekDao{
            return RetrofitClient.getClient(BASE_URL).create(YemekDao::class.java)
        }
    }
}