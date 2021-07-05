package com.italo.myweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.italo.myweather.dao.FavoriteCityDao
import com.italo.myweather.data.FavoriteCity

@Database(entities = [FavoriteCity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCityDao(): FavoriteCityDao
}
