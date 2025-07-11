package com.cristian.appgym.util

import android.content.Context
import com.cristian.appgym.model.model_ejercicio.Ejercicios
import com.cristian.appgym.model.model_ejercicio.EjerciciosCategorias
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LectorJSON(private val context: Context) {

    // Lee el archivo JSON de ejercicios y lo convierte a objeto Ejercicios
    fun leerEjercicios(): Ejercicios? {
        return try {
            val jsonString = context.assets.open("ejercicios.json").bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, Ejercicios::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Lee el archivo JSON de ejercicios por categorías
    fun leerEjerciciosCategorias(): EjerciciosCategorias? {
        return try {
            val jsonString = context.assets.open("ejercicios.json").bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, EjerciciosCategorias::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Método genérico para leer cualquier archivo JSON
    fun <T> leerJSON(nombreArchivo: String, clase: Class<T>): T? {
        return try {
            val jsonString = context.assets.open(nombreArchivo).bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, clase)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Método para leer listas desde JSON
    fun <T> leerListaJSON(nombreArchivo: String, tipoLista: TypeToken<List<T>>): List<T>? {
        return try {
            val jsonString = context.assets.open(nombreArchivo).bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, tipoLista.type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

