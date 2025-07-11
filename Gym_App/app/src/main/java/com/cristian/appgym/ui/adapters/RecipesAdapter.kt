package com.cristian.appgym.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cristian.appgym.databinding.ItemRecipeBinding
import com.cristian.appgym.model.model_receta.Recipe

class RecipesAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(
        private val binding: ItemRecipeBinding,
        private val onRecipeClick: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(recipe: Recipe) {
            binding.tvRecipeTitle.text = recipe.name
            // Aquí podrías cargar la imagen usando Glide o Picasso
            // Glide.with(binding.root).load(recipe.image).into(binding.ivRecipeImage)
            
            // Configurar click listener
            binding.btnViewRecipe.setOnClickListener {
                onRecipeClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return RecipeViewHolder(binding, onRecipeClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size
} 