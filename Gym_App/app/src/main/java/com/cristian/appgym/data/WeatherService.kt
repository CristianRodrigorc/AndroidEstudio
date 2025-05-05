package com.cristian.appgym.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.cristian.appgym.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Este servicio se encarga de obtener los datos del clima
// Usa la API de WeatherAPI.com que es gratuita y fácil de usar
class WeatherService(private val context: Context) {
    // Creamos la conexión con la API del clima
    private val weatherApi: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    // Obtiene el clima actual basado en la ubicación del dispositivo
    suspend fun getCurrentWeather(): WeatherResponse? {
        return try {
            // Primero obtenemos la ubicación actual
            val location = getLastKnownLocation()
            if (location != null) {
                // Si tenemos ubicación, hacemos la llamada a la API
                withContext(Dispatchers.IO) {
                    weatherApi.getCurrentWeather(
                        key = BuildConfig.WEATHER_API_KEY,
                        q = "${location.latitude},${location.longitude}",
                        lang = "es" // Pedimos los datos en español
                    )
                }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Obtiene la última ubicación conocida del dispositivo
    private fun getLastKnownLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // Verificamos si tenemos permisos de ubicación
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return null
        }
        // Obtenemos la ubicación usando el proveedor de red (más rápido que GPS)
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }
} 