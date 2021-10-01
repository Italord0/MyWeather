package com.italo.myweather.di

import android.content.Context
import androidx.room.Room
import com.italo.myweather.dao.FavoriteCityDao
import com.italo.myweather.db.AppDatabase

object FavoriteCityDBModule {

    lateinit var database: AppDatabase

    fun getFavoriteCityDao(): FavoriteCityDao {
        return database.favoriteCityDao()
    }

    fun init(appContext: Context) {
        database = Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME).build()
    }

    private const val DB_NAME = "myweather_db"
}
