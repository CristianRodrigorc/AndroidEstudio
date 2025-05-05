package com.cristian.appgym.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentSettingsBinding
import com.cristian.appgym.util.UtilidadesInterfaz
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class SettingsFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "AppSettings"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_THEME = "theme"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDrawer()
        setupButtons()
        loadSavedSettings()
    }

    private fun setupDrawer() {
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView

        binding.btnMenuSettings.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupButtons() {
        binding.btnChangeLanguage.setOnClickListener {
            changeLanguage()
        }

        binding.btnChangeTheme.setOnClickListener {
            changeTheme()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_user)
        }
    }

    private fun loadSavedSettings() {
        // Cargar idioma guardado
        val savedLanguage = sharedPreferences.getString(KEY_LANGUAGE, "es")
        val currentLocale = resources.configuration.locale
        if (savedLanguage != currentLocale.language) {
            val newLocale = Locale(savedLanguage!!)
            UtilidadesInterfaz.cambiarLenguaje(requireContext(), newLocale)
        }

        // Cargar tema guardado
        val isDarkTheme = sharedPreferences.getBoolean(KEY_THEME, false)
        val currentMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val shouldBeDark = currentMode == Configuration.UI_MODE_NIGHT_YES

        if (isDarkTheme != shouldBeDark) {
            val newMode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(newMode)
        }
    }

    private fun changeLanguage() {
        val currentLocale = resources.configuration.locale
        val newLocale = if (currentLocale.language == "es") {
            Locale("en")
        } else {
            Locale("es")
        }

        // Guardar preferencia de idioma
        sharedPreferences.edit().putString(KEY_LANGUAGE, newLocale.language).apply()

        UtilidadesInterfaz.cambiarLenguaje(requireContext(), newLocale)
        activity?.recreate()
    }

    private fun changeTheme() {
        val currentMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val newMode = when (currentMode) {
            Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
            else -> AppCompatDelegate.MODE_NIGHT_YES
        }

        // Guardar preferencia de tema
        val isDarkTheme = newMode == AppCompatDelegate.MODE_NIGHT_YES
        sharedPreferences.edit().putBoolean(KEY_THEME, isDarkTheme).apply()

        AppCompatDelegate.setDefaultNightMode(newMode)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mis_datos -> {
                findNavController().navigate(R.id.action_settings_to_userdata)
            }
            R.id.nav_progreso -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rutinas -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dieta -> {
                findNavController().navigate(R.id.action_settings_to_recipes)
            }
            R.id.nav_entrenador -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clases -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_tienda -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_comunidad -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clima -> {
                findNavController().navigate(R.id.action_settings_to_weather)
            }
            R.id.nav_configuracion -> {
                // Ya estamos en la sección de configuración
            }
        }
        drawerLayout.close()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 