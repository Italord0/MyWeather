package com.italo.myweather.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.italo.myweather.R
import com.italo.myweather.adapter.CityWeatherAdapter
import com.italo.myweather.data.City
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

        with(searchViewModel) {
            citiesLiveData.observe(
                viewLifecycleOwner,
                { response ->
                    mCityWeatherAdapter.setData(response)
                }
            )
            selectedCity.observe(
                viewLifecycleOwner,
                { city ->
                    handleSelectedCity(city)
                }
            )
        }

        binding.btnSearch.setOnClickListener {
            if (isInternetAvailable(requireContext())) {
                searchViewModel.getCity(binding.editTextCity.text.toString())
            } else {
                Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
            }
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

    private fun handleSelectedCity(city: City) {
        showDeleteDialog(city)
    }

    private fun showDeleteDialog(city: City) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.atention))
        builder.setMessage(getString(R.string.would_you_like_to_favorite_this_city))

        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            searchViewModel.saveFavoriteCity(city)
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }

        return result
    }
}
