package com.cristian.appgym.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cristian.appgym.databinding.FragmentWeatherBinding
import com.cristian.appgym.model.model_weather.WeatherService
import kotlinx.coroutines.launch

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherService: WeatherService

    // Launcher para solicitar permisos de ubicación
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.entries.all { it.value }
        if (allGranted) {
            obtenerClima()
        } else {
            Toast.makeText(context, "Se necesitan permisos de ubicación para obtener el clima", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        weatherService = WeatherService(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Verificar permisos de ubicación
        if (tienePermisosUbicacion()) {
            obtenerClima()
        } else {
            solicitarPermisosUbicacion()
        }
    }

    private fun tienePermisosUbicacion(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun solicitarPermisosUbicacion() {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun obtenerClima() {
        binding.tvLocation.text = "Obteniendo ubicación..."
        binding.tvTemperature.text = "--°C"
        binding.tvWeatherDescription.text = "Cargando..."
        binding.tvHumidity.text = "--%"
        binding.tvWind.text = "-- km/h"

        lifecycleScope.launch {
            try {
                val weatherResponse = weatherService.getCurrentWeather()
                if (weatherResponse != null) {
                    mostrarInformacionClima(weatherResponse)
                } else {
                    binding.tvLocation.text = "No se pudo obtener la ubicación"
                    binding.tvTemperature.text = "--°C"
                    binding.tvWeatherDescription.text = "Error al obtener datos"
                }
            } catch (e: Exception) {
                binding.tvLocation.text = "Error de conexión"
                binding.tvTemperature.text = "--°C"
                binding.tvWeatherDescription.text = "Error: ${e.message}"
            }
        }
    }

    private fun mostrarInformacionClima(weatherResponse: com.cristian.appgym.model.model_weather.WeatherResponse) {
        val current = weatherResponse.current
        val location = weatherResponse.location

        binding.tvLocation.text = "${location.name}, ${location.country}"
        binding.tvTemperature.text = "${current.temp_c.toInt()}°C"
        binding.tvWeatherDescription.text = current.condition.text
        binding.tvHumidity.text = "${current.humidity}%"
        binding.tvWind.text = "${current.wind_kph.toInt()} km/h"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 