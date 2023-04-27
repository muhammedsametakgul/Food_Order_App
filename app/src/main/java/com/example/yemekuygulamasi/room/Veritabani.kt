package com.example.yemekuygulamasi.room

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import com.example.yemekuygulamasi.data.entitiy.Favoriler

@Database(entities = [Favoriler::class], version = 2)
abstract  class Veritabani : RoomDatabase() {
    abstract  fun favorilerDao() : FavorilerDao

    companion object {//static
    var INSTANCE:Veritabani? = null

        fun veritabaniErisim(context: Context) : Veritabani? {
            if (INSTANCE == null){
                synchronized(Veritabani::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        Veritabani::class.java,
                        "favoriler.sqlite").createFromAsset("favoriler.sqlite").build()
                }
            }

            return INSTANCE
        }
    }
}