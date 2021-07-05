package com.italo.myweather.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.italo.myweather.adapter.CityWeatherAdapter
import com.italo.myweather.data.City
import com.italo.myweather.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    lateinit var cities: MutableList<City>
    lateinit var mCityWeatherAdapter: CityWeatherAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cities = ArrayList()
        mCityWeatherAdapter = CityWeatherAdapter(cities, this::onWeatherClickListener)
        setupRecyclerview()

        searchViewModel.citiesLiveData.observe(
            viewLifecycleOwner,
            { response ->
                cities.clear()
                cities.addAll(response)
                mCityWeatherAdapter.notifyDataSetChanged()
                println(cities)
            }
        )

        binding.btnSearch.setOnClickListener {
            searchViewModel.getCity(binding.editTextCity.text.toString())
        }
    }

    private fun onWeatherClickListener(city: City) {
        println(city.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerview() {
        binding.rvCityWeather.adapter = mCityWeatherAdapter
        val layoutManager = LinearLayoutManager(context)
        binding.rvCityWeather.layoutManager = layoutManager
    }
}
