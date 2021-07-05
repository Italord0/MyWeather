package com.italo.myweather.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.italo.myweather.data.City
import com.italo.myweather.domain.GetCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GetCityWeatherUseCase
) : ViewModel() {

    private val _citiesLiveData = MutableLiveData<List<City>>()

    val citiesLiveData: LiveData<List<City>>
        get() = _citiesLiveData

    fun getCity(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                useCase.getCity(name)
            }

            _citiesLiveData.postValue(response?.list)
        }
    }

    fun onCityClicked(it: City) {
        println(it.name)
    }
}
