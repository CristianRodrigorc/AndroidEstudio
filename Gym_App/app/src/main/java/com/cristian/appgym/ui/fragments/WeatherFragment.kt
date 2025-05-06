package com.cristian.appgym.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.data.WeatherResponse
import com.cristian.appgym.data.WeatherService
import com.cristian.appgym.databinding.FragmentWeatherBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

// Este fragmento muestra la información del clima actual
// Usa la ubicación del dispositivo para obtener datos precisos
class WeatherFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var weatherService: WeatherService

    // Este es el manejador para pedir permisos de ubicación
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Si nos dan permiso, cargamos los datos del clima
            loadWeatherData()
        } else {
            Toast.makeText(context, "Se necesitan permisos de ubicación para mostrar el clima", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        // Inicializamos el servicio del clima
        weatherService = WeatherService(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configuramos el menú lateral y verificamos los permisos
        setupDrawer()
        checkLocationPermission()
    }

    // Verifica si tenemos permisos de ubicación
    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Si ya tenemos permiso, cargamos los datos
                loadWeatherData()
            }
            else -> {
                // Si no tenemos permiso, lo pedimos
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // Carga los datos del clima desde la API
    private fun loadWeatherData() {
        lifecycleScope.launch {
            val weather = weatherService.getCurrentWeather()
            weather?.let {
                // Si tenemos datos, los mostramos en la pantalla
                updateUI(it)
            } ?: run {
                Toast.makeText(context, "No se pudo obtener la información del clima", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Actualiza la interfaz con los datos del clima
    private fun updateUI(weather: WeatherResponse) {
        binding.apply {
            tvLocation.text = "${weather.location.name}, ${weather.location.country}"
            tvTemperature.text = "${weather.current.temp_c.toInt()}°C"
            tvWeatherDescription.text = weather.current.condition.text
            tvHumidity.text = "Humedad: ${weather.current.humidity}%"
            tvWind.text = "Viento: ${weather.current.wind_kph.toInt()} km/h"
        }
    }

    // Configura el menú lateral
    private fun setupDrawer() {
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView

        binding.btnMenuWeather.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener(this)
    }

    // Maneja las opciones del menú lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mis_datos -> {
                findNavController().navigate(R.id.action_weather_to_userdata)
            }
            R.id.nav_progreso -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rutinas -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dieta -> {
                findNavController().navigate(R.id.action_weather_to_recipes)
            }
            R.id.nav_entrenador -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clases -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_tienda -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_comunidad -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clima -> {
                // Ya estamos en la sección de clima
            }
            R.id.nav_configuracion -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
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