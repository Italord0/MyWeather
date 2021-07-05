package com.italo.myweather.data

import com.google.gson.annotations.SerializedName

data class CityWeatherResponse(
    val message: String,
    val cod: String,
    val count: Long,
    val list: List<City>
)

data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val main: Main,
    val dt: Long,
    val wind: Wind,
    val sys: Sys,
    val rain: Any? = null,
    val snow: Any? = null,
    val clouds: Clouds,
    val weather: List<Weather>
)

data class Clouds(
    val all: Long
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    val pressure: Long,
    val humidity: Long,

    @SerializedName("sea_level")
    val seaLevel: Long,

    @SerializedName("grnd_level")
    val grndLevel: Long
)

data class Sys(
    val country: String
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double,
    val deg: Long
)
