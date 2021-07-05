package com.italo.myweather.domain

import com.italo.myweather.data.City
import com.italo.myweather.data.FavoriteCity
import com.italo.myweather.db.FavoriteCityRepository
import javax.inject.Inject

class FavoriteCityUseCase @Inject constructor(
    private val repository: FavoriteCityRepository
) {
    fun getFavoriteCities(): List<FavoriteCity> =
        repository.getFavoriteCities()

    fun deleteFavoriteCity(city: City) {
        repository.deleteFavoriteCity(city)
    }

    fun insertFavoriteCity(city: City) {
        repository.insertFavoriteCity(city)
    }
}
