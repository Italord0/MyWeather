package com.italo.myweather

import com.italo.myweather.api.CityWeatherRepository
import com.italo.myweather.api.WeatherApiService
import com.italo.myweather.data.CityWeatherResponse
import com.italo.myweather.preferences.Preferences
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CityWeatherRepositoryTest {

    @MockK
    private lateinit var weatherApiService: WeatherApiService

    @InjectMockKs
    private lateinit var cityWeatherRepository: CityWeatherRepository

    @Before
    fun before() {
        MockKAnnotations.init(this)
        mockkObject(Preferences)
        every {
            Preferences.getLangAPI()
        } returns "testeLang"
        every {
            Preferences.getUnitsAPI()
        } returns "testeUnit"
    }

    @Test
    fun `given query string then returns list with cities`() = runBlocking {
        coEvery {
            weatherApiService.getCities("Recife", any(), any(), any())
        } returns CityWeatherResponse("accurate", "200", 1, List(3) { mockk() })
        val result = cityWeatherRepository.getCities("Recife")
        assert(result?.list?.size == 3)
    }

    @Test
    fun `given ids should concat`() = runBlocking {
        prepareGetCitiesById()
        coVerify {
            weatherApiService.getCitiesById("1,2,3", any(), any(), any())
        }
    }

    @Test
    fun `getCitiesById should use lang in preferences`() = runBlocking {
        prepareGetCitiesById()
        coVerify {
            weatherApiService.getCitiesById(any(), any(), "testeLang", any())
        }
    }

    @Test
    fun `getCitiesById should use units in preferences`() = runBlocking {
        prepareGetCitiesById()
        coVerify {
            weatherApiService.getCitiesById(any(), any(), any(), "testeUnit")
        }
    }

    private suspend fun prepareGetCitiesById() {
        coEvery {
            weatherApiService.getCitiesById(any(), any(), any(), any())
        } returns mockk()
        cityWeatherRepository.getCitiesById(listOf(1L, 2L, 3L))
    }
}
