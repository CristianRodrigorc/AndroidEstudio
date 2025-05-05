package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentRecipesBinding
import com.cristian.appgym.ui.adapters.RecipesAdapter
import com.cristian.appgym.ui.models.Recipe
import com.google.android.material.navigation.NavigationView

class RecipesFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

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
        setupDrawer()
        setupUI()
        setupRecyclerView()
        loadRecipes()
    }

    private fun setupDrawer() {
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView

        binding.btnMenuRecipes.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupUI() {
        // Configuración de los chips de filtro
        setupFilterChips()
    }

    private fun setupRecyclerView() {
        recipesAdapter = RecipesAdapter { recipe ->
            showRecipeDetails(recipe)
        }
        
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
    }

    private fun showRecipeDetails(recipe: Recipe) {
        // Aquí irá la lógica para mostrar los detalles de la receta
        Toast.makeText(context, "Detalles de: ${recipe.title}", Toast.LENGTH_SHORT).show()
    }

    private fun loadRecipes() {
        val sampleRecipes = listOf(
            Recipe(
                id = 1,
                title = "Ensalada de Pollo y Aguacate",
                imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c",
                calories = 450,
                protein = 35,
                carbs = 20,
                fat = 25,
                type = "Proteica",
                ingredients = listOf("Pechuga de pollo", "Aguacate", "Lechuga", "Tomate", "Aceite de oliva"),
                instructions = "1. Cocinar el pollo\n2. Cortar los vegetales\n3. Mezclar todo"
            ),
            Recipe(
                id = 2,
                title = "Batido de Proteínas",
                imageUrl = "https://images.unsplash.com/photo-1502741224143-90386d7f8c82",
                calories = 300,
                protein = 30,
                carbs = 25,
                fat = 8,
                type = "Proteica",
                ingredients = listOf("Leche", "Proteína en polvo", "Plátano", "Mantequilla de maní"),
                instructions = "1. Mezclar todos los ingredientes\n2. Licuar hasta obtener consistencia suave"
            ),
            Recipe(
                id = 3,
                title = "Bowl de Quinoa y Verduras",
                imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd",
                calories = 400,
                protein = 15,
                carbs = 50,
                fat = 15,
                type = "Vegana",
                ingredients = listOf("Quinoa", "Brócoli", "Zanahoria", "Pimiento", "Aceite de oliva"),
                instructions = "1. Cocinar la quinoa\n2. Saltear las verduras\n3. Mezclar todo"
            )
        )
        
        recipesAdapter.submitList(sampleRecipes)
    }

    private fun setupFilterChips() {
        binding.chipAll.isChecked = true

        binding.chipAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                loadRecipes() // Recargar todas las recetas
            }
        }

        binding.chipKeto.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                filterRecipes("Keto")
            }
        }

        binding.chipVegana.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                filterRecipes("Vegana")
            }
        }

        binding.chipProteica.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                filterRecipes("Proteica")
            }
        }
    }

    private fun filterRecipes(type: String) {
        val filteredRecipes = recipesAdapter.currentList.filter { it.type == type }
        recipesAdapter.submitList(filteredRecipes)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mis_datos -> {
                findNavController().navigate(R.id.action_recipes_to_user)
            }
            R.id.nav_progreso -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rutinas -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dieta -> {
                // Ya estamos en la sección de dieta
            }
            R.id.nav_entrenador -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clases -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_tienda -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_comunidad -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_configuracion -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.close()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 