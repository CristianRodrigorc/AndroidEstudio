package com.cristian.appgym.ui.fragments

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentUserBinding
import com.cristian.appgym.model.model_ejercicio.Ejercicios
import com.cristian.appgym.model.model_ejercicio.EjerciciosCategorias
import com.cristian.appgym.ui.adapters.AvatarAdapter
import com.cristian.appgym.ui.viewmodel.UserViewModel
import com.cristian.appgym.util.LectorJSON
import com.cristian.appgym.util.UtilidadesBotones
import com.cristian.appgym.util.UtilidadesUsersScreen
import com.cristian.appgym.utils.SessionManager
import com.google.android.material.navigation.NavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class UserFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var viewModel: UserViewModel
    private var ejerciciosCompletados = 0
    private val totalEjercicios = 4

    companion object {
        private const val PREFS_NAME = "UserPrefs"
        private const val KEY_AVATAR_SEED = "avatar_seed"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        youtubePlayerView = binding.youtubeView
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Configurar el NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        // Configurar el botón de menú
        binding.btnMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView)
            } else {
                drawerLayout.openDrawer(navigationView)
            }
        }

        // Cargar el avatar guardado
        loadSavedAvatar()

        // Agregar el observador del ciclo de vida
        lifecycle.addObserver(youtubePlayerView)

        // Inicializar el reproductor de YouTube con manejo de errores
        try {
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    try {
                        youTubePlayer.loadVideo("uNN62f55EV0", 0f)
                        youTubePlayer.mute()
                    } catch (e: Exception) {
                        Log.e("UserFragment", "Error al cargar el video: ${e.message}")
                        Toast.makeText(context, "Error al cargar el video", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                    Log.e("UserFragment", "Error en el reproductor: $error")
                    Toast.makeText(context, getString(R.string.error_video_load), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Log.e("UserFragment", "Error al inicializar el reproductor: ${e.message}")
            Toast.makeText(context, "Error al inicializar el reproductor", Toast.LENGTH_SHORT).show()
        }

        setupCheckboxes()
        setupLogoutButton()
        setupToolbar()
        setupProgressBar()
        setupResetButton()
        setupDiaButtons()
        setupAvatarSelection()

        // Mostrar el nombre de usuario
        binding.tvUsername.text = sessionManager.getUsername()
    }

    private fun setupLogoutButton() {
        binding.btnLogout.setOnClickListener {
            sessionManager.clearSession()
            findNavController().navigate(R.id.action_user_to_home)
        }
    }

    private fun setupCheckboxes() {
        val chkBoxes = listOf(
            binding.chkEjercicio1,
            binding.chkEjercicio2,
            binding.chkEjercicio3,
            binding.chkEjercicio4
        )

        val containers = listOf(
            binding.containerEjercicio1,
            binding.containerEjercicio2,
            binding.containerEjercicio3,
            binding.containerEjercicio4
        )

        chkBoxes.forEachIndexed { index, chkBox ->
            chkBox.setOnCheckedChangeListener { _, isChecked ->
                val container = containers[index]
                if (isChecked) {
                    container.setBackgroundResource(R.drawable.rounded_background_gray)
                    ejerciciosCompletados++
                } else {
                    container.setBackgroundResource(R.drawable.rounded_background)
                    ejerciciosCompletados--
                }
                updateProgress()
                
                if (isChecked && index < chkBoxes.size - 1) {
                    chkBoxes[index + 1].isEnabled = true
                }
            }
        }

        chkBoxes.forEachIndexed { index, checkBox ->
            checkBox.isEnabled = index == 0
        }
    }

    private fun setupToolbar() {
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        binding.btnLogout.setOnClickListener {
            sessionManager.clearSession()
            findNavController().navigate(R.id.action_user_to_home)
        }
    }

    private fun setupProgressBar() {
        binding.progressBar.max = 100 // Cambiamos a 100 para que sea más claro
        resetProgress()
    }

    private fun updateProgress() {
        val progress = (ejerciciosCompletados.toFloat() / totalEjercicios.toFloat() * 100).toInt()
        binding.progressBar.progress = progress
    }

    private fun setupResetButton() {
        binding.btnReset.setOnClickListener {
            resetProgress()
        }
    }

    private fun resetProgress() {
        ejerciciosCompletados = 0
        val chkBoxes = listOf(
            binding.chkEjercicio1,
            binding.chkEjercicio2,
            binding.chkEjercicio3,
            binding.chkEjercicio4
        )
        
        val containers = listOf(
            binding.containerEjercicio1,
            binding.containerEjercicio2,
            binding.containerEjercicio3,
            binding.containerEjercicio4
        )
        
        chkBoxes.forEachIndexed { index, checkBox ->
            checkBox.isChecked = false
            checkBox.isEnabled = index == 0
            containers[index].setBackgroundResource(R.drawable.rounded_background)
        }
        
        updateProgress()
    }

    private fun setupDiaButtons() {
        val btnLunes = binding.btnLunes
        val btnMartes = binding.btnMartes
        val btnMiercoles = binding.btnMiercoles
        val btnJueves = binding.btnJueves
        val btnViernes = binding.btnViernes
        val btnSabado = binding.btnSabado
        val btnDomingo = binding.btnDomingo

        // Obtener las referencias de manera segura
        val tvEjercicio1 = binding.tvEjercicio1
        val tvRepeticiones1 = binding.tvRepeticiones1
        val tvEjercicio2 = binding.tvEjercicio2
        val tvRepeticiones2 = binding.tvRepeticiones2
        val tvEjercicio3 = binding.tvEjercicio3
        val tvRepeticiones3 = binding.tvRepeticiones3
        val tvEjercicio4 = binding.tvEjercicio4
        val tvRepeticiones4 = binding.tvRepeticiones4

        val lectorJSON = LectorJSON(requireContext())
        val ejerciciosJson = lectorJSON.leerEjercicios()
        val idVideoYTDefault = "uNN62f55EV0"
        
        // Verificar que se cargaron los ejercicios correctamente
        if (ejerciciosJson == null) {
            Log.e("UserFragment", "Error: No se pudieron cargar los ejercicios desde el JSON")
            Toast.makeText(requireContext(), "Error al cargar los ejercicios", Toast.LENGTH_SHORT).show()
        }
        
        // Cargar el video por defecto
        UtilidadesUsersScreen.cargarVideo(youtubePlayerView, idVideoYTDefault)

        val textViews = listOf(tvEjercicio1, tvEjercicio2, tvEjercicio3, tvEjercicio4)
        val repeticiones = listOf(tvRepeticiones1, tvRepeticiones2, tvRepeticiones3, tvRepeticiones4)
        val switches = listOf(
            binding.chkEjercicio1,
            binding.chkEjercicio2,
            binding.chkEjercicio3,
            binding.chkEjercicio4
        )

        // Videos para cada día
        val videosLunes = listOf("3k0Iu_ogVtw", "oq42eVowbD4", "lG53BKnQlxY", "Is3JRhq37o4")
        val videosMartes = listOf("aLtLLvffF6M", "vTdr4UKscRE", "gY-CqZD0Ktc", "uDjXYcXR0ys")
        val videosMiercoles = listOf("8LR0mo8iD8s", "vw_pcm2ly5Y", "ItBASjwB_Wo", "SnNyF9g8dDE")
        val videosJueves = listOf("Vg1nmlJzGgM", "31vdmfx5pJs", "JddDALFiRbw", "SCsH7Z7qDwU")
        val videosViernes = listOf("ozCH5r2lP2E", "MetkFq2hMZs", "FyWvCXvCC-w", "Pe9fw_B-B34")
        val videosSabado = listOf("Xaa6rn3Hrh4", "DAMw-xGYNck", "9V8eNF-fkls", "bheSKL7AhGY")
        val videosDomingo = listOf("QMvyEWQrmsY", "2ypB_CmVILM", "P60uxBkcaNI", "rMznoDrT5aI")

        val onClickListener = View.OnClickListener { view ->
            resetProgress() // Reiniciar el progreso al cambiar de día
            
            // Verificar que los ejercicios estén cargados
            if (ejerciciosJson == null) {
                Toast.makeText(requireContext(), "Error: No se pudieron cargar los ejercicios", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            
            when (view.id) {
                R.id.btnLunes -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.biceps,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosLunes
                    )
                }
                R.id.btnMartes -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.triceps,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosMartes
                    )
                }
                R.id.btnMiercoles -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.pecho,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosMiercoles
                    )
                }
                R.id.btnJueves -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.espalda,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosJueves
                    )
                }
                R.id.btnViernes -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.piernas,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosViernes
                    )
                }
                R.id.btnSabado -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.hombros,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosSabado
                    )
                }
                R.id.btnDomingo -> {
                    UtilidadesUsersScreen.llenarDiaGenerico(
                        ejerciciosJson.ejercicios.abdomen,
                        textViews,
                        repeticiones,
                        switches,
                        youtubePlayerView,
                        videosDomingo
                    )
                }
            }
        }

        btnLunes.setOnClickListener(onClickListener)
        btnMartes.setOnClickListener(onClickListener)
        btnMiercoles.setOnClickListener(onClickListener)
        btnJueves.setOnClickListener(onClickListener)
        btnViernes.setOnClickListener(onClickListener)
        btnSabado.setOnClickListener(onClickListener)
        btnDomingo.setOnClickListener(onClickListener)
    }

    private fun setupAvatarSelection() {
        binding.cardAvatar.setOnClickListener {
            showAvatarSelectionDialog()
        }
    }

    private fun loadSavedAvatar() {
        val sharedPrefs = requireContext().getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
        val savedSeed = sharedPrefs.getString(KEY_AVATAR_SEED, null)
        
        if (savedSeed != null) {
            val url = "https://api.dicebear.com/7.x/adventurer/png?seed=$savedSeed&size=128"
            Glide.with(requireContext())
                .load(url)
                .into(binding.ivUserAvatar)
        }
    }

    private fun showAvatarSelectionDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_avatar_selection)
        
        val recyclerView = dialog.findViewById<RecyclerView>(R.id.rvAvatars)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        
        recyclerView.adapter = AvatarAdapter { selectedSeed ->
            // Guardar el seed seleccionado
            val sharedPrefs = requireContext().getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
            sharedPrefs.edit().putString(KEY_AVATAR_SEED, selectedSeed).apply()
            
            // Actualizar el avatar del usuario
            val url = "https://api.dicebear.com/7.x/adventurer/png?seed=$selectedSeed&size=128"
            Glide.with(requireContext())
                .load(url)
                .into(binding.ivUserAvatar)
            
            dialog.dismiss()
        }
        
        dialog.show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_profile -> {
                binding.drawerLayout.close()
                findNavController().navigate(R.id.action_user_to_profile)
                true
            }
            R.id.nav_progreso -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_rutinas -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_dieta -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_entrenador -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_clases -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_tienda -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_comunidad -> {
                binding.drawerLayout.close()
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_clima -> {
                binding.drawerLayout.close()
                findNavController().navigate(R.id.action_user_to_weather)
                true
            }
            R.id.nav_configuracion -> {
                binding.drawerLayout.close()
                findNavController().navigate(R.id.action_user_to_settings)
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            youtubePlayerView.release()
        } catch (e: Exception) {
            Log.e("UserFragment", "Error al liberar el reproductor: ${e.message}")
        }
        _binding = null
    }
}