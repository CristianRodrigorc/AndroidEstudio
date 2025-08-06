package com.cristian.appgym.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cristian.appgym.R
import com.cristian.appgym.model.model_receta.Recipe

class RecipesAdapter(
    private var recipes: List<Recipe> = emptyList(),
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivRecipeImage: ImageView = itemView.findViewById(R.id.ivRecipeImage)
        private val tvRecipeTitle: TextView = itemView.findViewById(R.id.tvRecipeTitle)
        private val tvRecipeType: TextView = itemView.findViewById(R.id.tvRecipeType)
        private val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
        private val tvProtein: TextView = itemView.findViewById(R.id.tvProtein)
        private val tvCarbs: TextView = itemView.findViewById(R.id.tvCarbs)
        private val tvFat: TextView = itemView.findViewById(R.id.tvFat)

        fun bind(recipe: Recipe) {
            tvRecipeTitle.text = recipe.title
            tvRecipeType.text = recipe.type ?: "Sin categoría"
            
            // Mostrar información nutricional
            recipe.calories?.let { tvCalories.text = "$it cal" }
            recipe.protein?.let { tvProtein.text = "P: ${it}g" }
            recipe.carbs?.let { tvCarbs.text = "C: ${it}g" }
            recipe.fat?.let { tvFat.text = "G: ${it}g" }

            // Cargar imagen con Glide
            recipe.imageUrl?.let { url ->
                Glide.with(itemView.context)
                    .load(url)
                    .placeholder(R.drawable.placeholder_recipe)
                    .error(R.drawable.error_recipe)
                    .into(ivRecipeImage)
            } ?: run {
                ivRecipeImage.setImageResource(R.drawable.placeholder_recipe)
            }

            itemView.setOnClickListener {
                onRecipeClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
} 