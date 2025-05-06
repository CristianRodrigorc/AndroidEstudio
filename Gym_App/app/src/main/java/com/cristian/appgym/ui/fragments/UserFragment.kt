package com.cristian.appgym.ui.fragments

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
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentUserBinding
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
                    }
                }

                override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                    Log.e("UserFragment", "Error en el reproductor: $error")
                    Toast.makeText(context, getString(R.string.error_video_load), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Log.e("UserFragment", "Error al inicializar el reproductor: ${e.message}")
        }

        setupNavigationDrawer()
        setupCheckboxes()
        setupLogoutButton()
        setupToolbar()
        setupProgressBar()
        setupResetButton()
        setupDiaButtons()

        // Mostrar el nombre de usuario
        binding.tvUsername.text = sessionManager.getUsername()
    }

    private fun setupNavigationDrawer() {
        binding.btnMenu.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener(this)
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
        binding.btnMenu.setOnClickListener {
            drawerLayout.open()
        }

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
        val btnLunes = view?.findViewById<Button>(R.id.btnLunes)
        val btnMartes = view?.findViewById<Button>(R.id.btnMartes)
        val btnMiercoles = view?.findViewById<Button>(R.id.btnMiercoles)
        val btnJueves = view?.findViewById<Button>(R.id.btnJueves)
        val btnViernes = view?.findViewById<Button>(R.id.btnViernes)
        val btnSabado = view?.findViewById<Button>(R.id.btnSabado)
        val btnDomingo = view?.findViewById<Button>(R.id.btnDomingo)

        val tvEjercicio1 = view!!.findViewById<TextView>(R.id.tvEjercicio1)
        val tvRepeticiones1 = view!!.findViewById<TextView>(R.id.tvRepeticiones1)
        val tvEjercicio2 = view!!.findViewById<TextView>(R.id.tvEjercicio2)
        val tvRepeticiones2 = view!!.findViewById<TextView>(R.id.tvRepeticiones2)
        val tvEjercicio3 = view!!.findViewById<TextView>(R.id.tvEjercicio3)
        val tvRepeticiones3 = view!!.findViewById<TextView>(R.id.tvRepeticiones3)
        val tvEjercicio4 = view!!.findViewById<TextView>(R.id.tvEjercicio4)
        val tvRepeticiones4 = view!!.findViewById<TextView>(R.id.tvRepeticiones4)

        val ejerciciosJson = LectorJSON.obtenerJsonGson(requireContext())
        val idVideoYTDefault = "uNN62f55EV0"
        
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
            when (view.id) {
                R.id.btnLunes -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.biceps,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosLunes
                        )
                    }
                }
                R.id.btnMartes -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.triceps,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosMartes
                        )
                    }
                }
                R.id.btnMiercoles -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.pecho,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosMiercoles
                        )
                    }
                }
                R.id.btnJueves -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.espalda,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosJueves
                        )
                    }
                }
                R.id.btnViernes -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.piernas,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosViernes
                        )
                    }
                }
                R.id.btnSabado -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.hombros,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosSabado
                        )
                    }
                }
                R.id.btnDomingo -> {
                    ejerciciosJson?.let {
                        UtilidadesUsersScreen.llenarDiaGenerico(
                            it.ejercicios.abdomen,
                            textViews,
                            repeticiones,
                            switches,
                            youtubePlayerView,
                            videosDomingo
                        )
                    }
                }
            }
        }

        btnLunes?.setOnClickListener(onClickListener)
        btnMartes?.setOnClickListener(onClickListener)
        btnMiercoles?.setOnClickListener(onClickListener)
        btnJueves?.setOnClickListener(onClickListener)
        btnViernes?.setOnClickListener(onClickListener)
        btnSabado?.setOnClickListener(onClickListener)
        btnDomingo?.setOnClickListener(onClickListener)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mis_datos -> {
                findNavController().navigate(R.id.action_user_to_userdata)
            }
            R.id.nav_progreso -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rutinas -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dieta -> {
                findNavController().navigate(R.id.action_user_to_recipes)
            }
            R.id.nav_entrenador -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clases -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_tienda -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_comunidad -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_clima -> {
                findNavController().navigate(R.id.action_user_to_weather)
            }
            R.id.nav_configuracion -> {
                Toast.makeText(context, getString(R.string.feature_development), Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.close()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youtubePlayerView.release()
        _binding = null
    }
}