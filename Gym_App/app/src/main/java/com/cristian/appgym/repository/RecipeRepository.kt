package com.cristian.appgym.repository

import com.cristian.appgym.model.model_receta.Recipe
import com.cristian.appgym.network.ApiService
import com.cristian.appgym.utils.Result

class RecipeRepository(private val apiService: ApiService) {

    // Obtener todas las recetas
    suspend fun getAllRecipes(): Result<List<Recipe>> {
        return try {
            val response = apiService.getAllRecipes()
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al obtener las recetas: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener receta por ID
    suspend fun getRecipeById(id: Int): Result<Recipe> {
        return try {
            val response = apiService.getRecipeById(id)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Receta no encontrada")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Buscar recetas por título
    suspend fun searchRecipes(query: String): Result<List<Recipe>> {
        return try {
            val response = apiService.searchRecipes(query)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al buscar recetas: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener recetas por tipo
    suspend fun getRecipesByType(type: String): Result<List<Recipe>> {
        return try {
            val response = apiService.getRecipesByType(type)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al obtener recetas del tipo: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener recetas por rango de calorías
    suspend fun getRecipesByCaloriesRange(min: Int, max: Int): Result<List<Recipe>> {
        return try {
            val response = apiService.getRecipesByCaloriesRange(min, max)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al obtener recetas por calorías: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener recetas altas en proteína
    suspend fun getHighProteinRecipes(minProtein: Int = 20): Result<List<Recipe>> {
        return try {
            val response = apiService.getHighProteinRecipes(minProtein)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al obtener recetas altas en proteína: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }
} 