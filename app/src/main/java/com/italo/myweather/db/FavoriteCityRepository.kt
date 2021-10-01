package com.italo.myweather.db

import com.italo.myweather.dao.FavoriteCityDao
import com.italo.myweather.data.City
import com.italo.myweather.data.FavoriteCity
import com.italo.myweather.di.FavoriteCityDBModule

class FavoriteCityRepository(
    private val favoriteCityDao: FavoriteCityDao = FavoriteCityDBModule.getFavoriteCityDao()
) {
    fun getFavoriteCities(): List<FavoriteCity> =
        favoriteCityDao.getAll()

    fun insertFavoriteCity(city: City) {
        favoriteCityDao.insertFavoriteCity(FavoriteCity(city.id))
    }

    fun deleteFavoriteCity(city: City) {
        favoriteCityDao.deleteFavoriteCity(FavoriteCity(city.id))
    }
}
