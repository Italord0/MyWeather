package com.italo.myweather.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.italo.myweather.databinding.FragmentSettingsBinding
import com.italo.myweather.preferences.Preferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val settingsViewModel: SettingsViewModel by viewModels()
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rgTemperature.check(Preferences.getTemperatureId())
        binding.rgLanguage.check(Preferences.getLanguageId())

        binding.btnSave.setOnClickListener {

            val shouldRecreateLayout: Boolean =
                binding.rgLanguage.checkedRadioButtonId != Preferences.getLanguageId()

            Preferences.set("TEMPERATURE", binding.rgTemperature.checkedRadioButtonId)
            Preferences.set("LANGUAGE", binding.rgLanguage.checkedRadioButtonId)

            if (shouldRecreateLayout) {
                activity?.recreate()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
