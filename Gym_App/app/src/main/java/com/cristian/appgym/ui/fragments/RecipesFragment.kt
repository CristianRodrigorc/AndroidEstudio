package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.appgym.databinding.FragmentRecipesBinding
import com.cristian.appgym.model.model_receta.Recipe
import com.cristian.appgym.ui.adapters.RecipesAdapter
import com.cristian.appgym.viewmodel.RecipeViewModel

class RecipesFragment : Fragment() {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipesAdapter: RecipesAdapter

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
        
        // Inicializar ViewModel
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        
        setupRecyclerView()
        setupObservers()
        setupSearch()
        setupChipFilters()
    }

    private fun setupRecyclerView() {
        recipesAdapter = RecipesAdapter(emptyList()) { recipe ->
            // Manejar el click en la receta
            Toast.makeText(context, "Receta seleccionada: ${recipe.title}", Toast.LENGTH_SHORT).show()
            // Aquí podrías navegar a una pantalla de detalles de la receta
        }
        
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
    }

    private fun setupObservers() {
        // Observar cambios en las recetas
        recipeViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            recipesAdapter.updateRecipes(recipes)
            updateEmptyState(recipes.isEmpty())
        }

        // Observar estado de carga
        recipeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarRecipes.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observar errores
        recipeViewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                recipeViewModel.clearError()
            }
        }
    }

    private fun setupSearch() {
        // Configurar búsqueda si tienes un SearchView
        binding.searchViewRecipes?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { recipeViewModel.searchRecipes(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Opcional: búsqueda en tiempo real
                return false
            }
        })
    }

    private fun setupChipFilters() {
        // Configurar los chips de filtro
        binding.chipAll.setOnClickListener {
            recipeViewModel.loadRecipes()
        }
        
        binding.chipKeto.setOnClickListener {
            recipeViewModel.getRecipesByType("Cena") // Usar tipo en lugar de categoría
        }
        
        binding.chipVegana.setOnClickListener {
            recipeViewModel.getRecipesByType("Snack") // Usar tipo en lugar de categoría
        }
        
        binding.chipProteica.setOnClickListener {
            recipeViewModel.getHighProteinRecipes(20) // Obtener recetas altas en proteína
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.tvEmptyState?.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.rvRecipes.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 