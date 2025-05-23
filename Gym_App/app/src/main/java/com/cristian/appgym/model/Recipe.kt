package com.cristian.appgym.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val type: String,
    val ingredients: List<String>,
    val instructions: String
)