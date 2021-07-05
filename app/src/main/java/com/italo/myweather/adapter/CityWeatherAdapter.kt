package com.italo.myweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.italo.myweather.R
import com.italo.myweather.data.City
import com.italo.myweather.databinding.ItemWeatherBinding
import com.italo.myweather.preferences.Preferences
import kotlin.math.roundToInt

class CityWeatherAdapter(
    private val cities: List<City>,
    private val callback: (City) -> Unit
) : RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        val binding = ItemWeatherBinding.bind(view)
        val vh = ViewHolder(binding)
        vh.itemView.setOnClickListener {
            val city = cities[vh.adapterPosition]
            callback(city)
        }
        return vh
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.tvCityName.text = city.name
        holder.tvCityTemp.text =
            city.main.temp.roundToInt().toString().plus(Preferences.getTemperature())
    }

    class ViewHolder(itemView: ItemWeatherBinding) : RecyclerView.ViewHolder(itemView.root) {
        val ivCityWeather: ImageView = itemView.ivCityWeather
        val ivCityCountry: ImageView = itemView.ivCityCountry
        val tvCityName: TextView = itemView.tvCityName
        val tvCityTemp: TextView = itemView.tvCityTemp
    }
}