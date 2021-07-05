package com.italo.myweather.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.italo.myweather.R
import com.italo.myweather.adapter.CityWeatherAdapter
import com.italo.myweather.data.City
import com.italo.myweather.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null

    private lateinit var mCityWeatherAdapter: CityWeatherAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        mCityWeatherAdapter =
            CityWeatherAdapter {
                favoriteViewModel.onCityClicked(it)
            }

        setupRecyclerView()

        favoriteViewModel.getFavoritesFromDb()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(favoriteViewModel) {
            favoriteCitiesDbLiveData.observe(
                viewLifecycleOwner,
                {
                    favoriteViewModel.getFavoritesFromApi(it)
                }
            )
            favoriteCitiesLiveData.observe(
                viewLifecycleOwner,
                {
                    mCityWeatherAdapter.setData(it)
                }
            )
            selectedCity.observe(
                viewLifecycleOwner,
                {
                    handleSelectedCity(it)
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.rvCityWeather.adapter = mCityWeatherAdapter
        val layoutManager = LinearLayoutManager(context)
        binding.rvCityWeather.layoutManager = layoutManager
    }

    private fun handleSelectedCity(city: City) {
        showRemoveFavoriteDialog(city)
    }

    private fun showRemoveFavoriteDialog(city: City) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.atention))
        builder.setMessage(getString(R.string.would_you_like_to_remove_this_city_from_your_favorite_list))

        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            favoriteViewModel.removeFavoriteCity(city)
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
