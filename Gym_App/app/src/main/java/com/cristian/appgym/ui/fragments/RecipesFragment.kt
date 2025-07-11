package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.appgym.databinding.FragmentRecipesBinding
import com.cristian.appgym.model.model_receta.Recipe
import com.cristian.appgym.ui.adapters.RecipesAdapter

class RecipesFragment : Fragment() {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recipes = listOf(
            Recipe(1, "Ensalada de Pollo", "Ensalada saludable con pollo a la plancha", "salad.jpg"),
            Recipe(2, "Batido de Proteínas", "Batido post-entrenamiento con proteínas", "shake.jpg"),
            Recipe(3, "Avena con Frutas", "Desayuno nutritivo con avena y frutas", "oatmeal.jpg"),
            Recipe(4, "Pechuga de Pollo", "Pechuga de pollo a la plancha con verduras", "chicken.jpg"),
            Recipe(5, "Salmón al Horno", "Salmón rico en omega-3 al horno", "salmon.jpg"),
            Recipe(6, "Quinoa Bowl", "Bowl nutritivo con quinoa y vegetales", "quinoa.jpg")
        )

        val adapter = RecipesAdapter(recipes) { recipe ->
            // Manejar el click en la receta
            Toast.makeText(context, "Receta seleccionada: ${recipe.name}", Toast.LENGTH_SHORT).show()
            // Aquí podrías navegar a una pantalla de detalles de la receta
        }
        
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 