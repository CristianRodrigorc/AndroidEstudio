package com.cristian.appgym.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.appgym.model.model_receta.Recipe
import com.cristian.appgym.network.RetrofitClient
import com.cristian.appgym.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    
    private val recipeRepository = RecipeRepository(RetrofitClient.apiService)
    
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private val _selectedRecipe = MutableLiveData<Recipe?>()
    val selectedRecipe: LiveData<Recipe?> = _selectedRecipe

    init {
        loadRecipes()
    }

    // Cargar todas las recetas
    fun loadRecipes() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            when (val result = recipeRepository.getAllRecipes()) {
                is com.cristian.appgym.utils.Result.Success -> {
                    _recipes.value = result.data
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    // Buscar recetas
    fun searchRecipes(query: String) {
        if (query.isBlank()) {
            loadRecipes()
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            when (val result = recipeRepository.searchRecipes(query)) {
                is com.cristian.appgym.utils.Result.Success -> {
                    _recipes.value = result.data
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }



    // Obtener recetas por tipo
    fun getRecipesByType(type: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            when (val result = recipeRepository.getRecipesByType(type)) {
                is com.cristian.appgym.utils.Result.Success -> {
                    _recipes.value = result.data
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    // Obtener recetas por rango de calorías
    fun getRecipesByCaloriesRange(min: Int, max: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            when (val result = recipeRepository.getRecipesByCaloriesRange(min, max)) {
                is com.cristian.appgym.utils.Result.Success -> {
                    _recipes.value = result.data
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    // Obtener recetas altas en proteína
    fun getHighProteinRecipes(minProtein: Int = 20) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            when (val result = recipeRepository.getHighProteinRecipes(minProtein)) {
                is com.cristian.appgym.utils.Result.Success -> {
                    _recipes.value = result.data
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    // Obtener receta por ID
    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            when (val result = recipeRepository.getRecipeById(id)) {
                is com.cristian.appgym.utils.Result.Success -> {
                    _selectedRecipe.value = result.data
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is com.cristian.appgym.utils.Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    // Seleccionar una receta
    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    // Limpiar error
    fun clearError() {
        _error.value = null
    }

    // Recargar recetas
    fun refreshRecipes() {
        loadRecipes()
    }
} 