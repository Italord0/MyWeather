package com.italo.myweather.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.italo.myweather.data.CityWeatherResponse
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

    val cityLiveData = MutableLiveData<CityWeatherResponse>()

    fun getCity(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val city = withContext(Dispatchers.Default) {
                useCase.getCity(name)
            }

            cityLiveData.value = city
        }
    }
}
