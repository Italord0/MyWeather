package com.italo.myweather.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.italo.myweather.adapter.CityWeatherAdapter
import com.italo.myweather.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null

    private lateinit var mCityWeatherAdapter: CityWeatherAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        mCityWeatherAdapter =
            CityWeatherAdapter {
                searchViewModel.onCityClicked(it)
            }
        setupRecyclerview()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.citiesLiveData.observe(
            viewLifecycleOwner,
            { response ->
                mCityWeatherAdapter.setData(response)
            }
        )

        binding.btnSearch.setOnClickListener {
            searchViewModel.getCity(binding.editTextCity.text.toString())
        }
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
