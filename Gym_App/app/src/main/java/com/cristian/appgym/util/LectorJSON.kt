package com.cristian.appgym.util

import android.content.Context
import android.util.Log
import com.cristian.appgym.model.Ejercicios
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

object LectorJSON {


    fun obtenerJsonGson(context: Context): Ejercicios? {
        return try {
            val jsonString = cargarArchivoDesdeAssets(context, "ejercicios.json") // Aquí se asume que tu archivo se llama ejercicios.json
            val gson = Gson()
            val type = object : TypeToken<Ejercicios>() {}.type
            gson.fromJson(jsonString, type)
        } catch (e: Exception) {
            Log.e("GsonError", "Error al leer el archivo JSON", e)
            null
        }
    }

    private fun cargarArchivoDesdeAssets(context: Context, fileName: String): String {
        val inputStream: InputStream = context.assets.open(fileName)
        return inputStream.bufferedReader().use { it.readText() }
    }
}

