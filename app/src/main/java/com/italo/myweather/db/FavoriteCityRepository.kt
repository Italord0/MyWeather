package com.italo.myweather.db

import com.italo.myweather.dao.FavoriteCityDao
import com.italo.myweather.data.City
import com.italo.myweather.data.FavoriteCity
import javax.inject.Inject

class FavoriteCityRepository @Inject constructor(
    private val favoriteCityDao: FavoriteCityDao
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
