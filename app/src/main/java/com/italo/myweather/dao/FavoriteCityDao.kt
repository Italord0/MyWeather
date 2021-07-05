package com.italo.myweather.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.italo.myweather.data.FavoriteCity

@Dao
interface FavoriteCityDao {

    @Query("SELECT * FROM favoritecity")
    fun getAll(): List<FavoriteCity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteCity(city: FavoriteCity)

    @Delete
    fun deleteFavoriteCity(city: FavoriteCity)
}
