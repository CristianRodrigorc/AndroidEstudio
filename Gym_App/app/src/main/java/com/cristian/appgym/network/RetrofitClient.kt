package com.cristian.appgym.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    // CONFIGURACIÓN DE URL DEL BACKEND
    private const val BASE_URL = "https://backendgymapp.onrender.com/"

    // Interceptor para logging - Muestra todas las peticiones HTTP en el log
    // Útil para debugging: ver qué se envía y qué se recibe del backend
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Configuración del cliente HTTP con timeouts y reintentos
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)  // Agrega el logging para ver las peticiones
        .connectTimeout(30, TimeUnit.SECONDS)  // Tiempo máximo para establecer conexión
        .readTimeout(30, TimeUnit.SECONDS)     // Tiempo máximo para leer respuesta
        .writeTimeout(30, TimeUnit.SECONDS)    // Tiempo máximo para escribir datos
        .retryOnConnectionFailure(true)        // Reintenta si falla la conexión
        .build()

    // Configuración de Retrofit para hacer peticiones HTTP
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)  // URL base del backend
        .client(okHttpClient)  // Usa el cliente HTTP configurado
        .addConverterFactory(GsonConverterFactory.create())  // Convierte JSON a objetos Kotlin
        .build()

    // Servicio consolidado para todas las operaciones del backend
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}