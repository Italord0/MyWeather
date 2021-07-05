package com.italo.myweather.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.italo.myweather.data.City
import com.italo.myweather.db.FavoriteCityRepository
import com.italo.myweather.domain.GetCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GetCityWeatherUseCase,
    private val repository: FavoriteCityRepository
) : ViewModel() {

    private val _citiesLiveData = MutableLiveData<List<City>>()

    private val _citySelected = MutableLiveData<City>()

    val citiesLiveData: LiveData<List<City>>
        get() = _citiesLiveData

    val selectedCity: LiveData<City>
        get() = _citySelected

    fun getCity(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                useCase.getCities(name)
            }

            _citiesLiveData.postValue(response?.list)
        }
    }

    fun onCityClicked(it: City) {
        _citySelected.postValue(it)
    }

    fun saveFavoriteCity(city: City) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                repository.insertFavoriteCity(city)
            }
        }
    }
}
