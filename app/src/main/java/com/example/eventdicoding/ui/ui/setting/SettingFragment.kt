package com.example.eventdicoding.ui.ui.setting

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.eventdicoding.R
import com.example.eventdicoding.ui.ThemePreferences
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.launch


class SettingFragment : Fragment() {

    private lateinit var themePreferences: ThemePreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        themePreferences = ThemePreferences(requireContext())
        val switchTheme = view.findViewById<SwitchMaterial>(R.id.switch_theme)

        lifecycleScope.launch {
            themePreferences.darkModeFlow.collect { isDarkMode ->
                switchTheme.isChecked = isDarkMode
                AppCompatDelegate.setDefaultNightMode(
                    if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                themePreferences.saveDarkModeSetting(isChecked)
            }
        }

        return view
    }
}