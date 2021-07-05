package com.italo.myweather.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.italo.myweather.data.City
import com.italo.myweather.data.FavoriteCity
import com.italo.myweather.domain.FavoriteCityUseCase
import com.italo.myweather.domain.GetCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteCityUseCase: FavoriteCityUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : ViewModel() {

    private val _favoriteCitiesDbLiveData = MutableLiveData<List<FavoriteCity>>()

    val favoriteCitiesDbLiveData: LiveData<List<FavoriteCity>>
        get() = _favoriteCitiesDbLiveData

    private val _favoriteCitiesLiveData = MutableLiveData<List<City>>()

    val favoriteCitiesLiveData: LiveData<List<City>>
        get() = _favoriteCitiesLiveData

    private val _citySelected = MutableLiveData<City>()

    val selectedCity: LiveData<City>
        get() = _citySelected

    fun getFavoritesFromDb() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                favoriteCityUseCase.getFavoriteCities()
            }
            _favoriteCitiesDbLiveData.postValue(result)
        }
    }

    fun getFavoritesFromApi(favorites: List<FavoriteCity>) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                getCityWeatherUseCase.getCitiesById(favorites.map { it.id })
            }
            _favoriteCitiesLiveData.postValue(response?.list)
        }
    }

    fun onCityClicked(city: City) {
        _citySelected.postValue(city)
    }

    fun removeFavoriteCity(city: City) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                favoriteCityUseCase.deleteFavoriteCity(city)
                getFavoritesFromDb()
            }
        }
    }
}
