package com.example.yemekuygulamasi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yemekuygulamasi.data.entitiy.Favoriler

@Dao
interface FavorilerDao {
    @Query("SELECT * FROM favoriler")
    suspend fun tumFavoriler() : List<Favoriler>

    @Insert
    suspend fun favoriEkle(favori : Favoriler)

    @Delete
    suspend fun  favoriSil(favori: Favoriler)
}