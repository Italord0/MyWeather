package com.italo.myweather.ui.search

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

    val citiesLiveData = MutableLiveData<List<City>>()

    fun getCity(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                useCase.getCity(name)
            }

            citiesLiveData.value = response?.list
        }
    }
}
