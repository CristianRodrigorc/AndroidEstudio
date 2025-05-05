package com.cristian.appgym.ui.fragments

import android.os.Bundle
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
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentUserBinding
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

        // Agregar el observador del ciclo de vida
        lifecycle.addObserver(youtubePlayerView)

        // Inicializar el reproductor de YouTube
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo("uNN62f55EV0", 0f)
                youTubePlayer.mute()
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                // Manejar errores del reproductor
                Toast.makeText(context, "Error al cargar el video", Toast.LENGTH_SHORT).show()
            }
        })

        setupNavigationDrawer()
        setupCheckboxes()
        setupLogoutButton()
        setupToolbar()

        // Mostrar el nombre de usuario
        binding.tvUsername.text = sessionManager.getUsername()

        // Configurar el resto de la funcionalidad existente
        val btnLunes = view.findViewById<Button>(R.id.btnLunes)
        val btnMartes = view.findViewById<Button>(R.id.btnMartes)
        val btnMiercoles = view.findViewById<Button>(R.id.btnMiercoles)
        val btnJueves = view.findViewById<Button>(R.id.btnJueves)
        val btnViernes = view.findViewById<Button>(R.id.btnViernes)
        val btnSabado = view.findViewById<Button>(R.id.btnSabado)
        val btnDomingo = view.findViewById<Button>(R.id.btnDomingo)

        val tvEjercicio1 = view.findViewById<TextView>(R.id.tvEjercicio1)
        val descEjercicio1 = view.findViewById<TextView>(R.id.descEjercicio1)
        val tvEjercicio2 = view.findViewById<TextView>(R.id.tvEjercicio2)
        val descEjercicio2 = view.findViewById<TextView>(R.id.descEjercicio2)
        val tvEjercicio3 = view.findViewById<TextView>(R.id.tvEjercicio3)
        val descEjercicio3 = view.findViewById<TextView>(R.id.descEjercicio3)
        val tvEjercicio4 = view.findViewById<TextView>(R.id.tvEjercicio4)
        val descEjercicio4 = view.findViewById<TextView>(R.id.descEjercicio4)

        val ejerciciosJson = LectorJSON.obtenerJsonGson(requireContext())
        val idVideoYTDefault = "uNN62f55EV0"
        
        // Cargar el video por defecto
        UtilidadesUsersScreen.cargarVideo(youtubePlayerView, idVideoYTDefault)

        val textViews = listOf(tvEjercicio1, tvEjercicio2, tvEjercicio3, tvEjercicio4)
        val descripciones = listOf(descEjercicio1, descEjercicio2, descEjercicio3, descEjercicio4)
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

        btnLunes.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.biceps,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosLunes
                )
            }
        }

        btnMartes.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.triceps,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosMartes
                )
            }
        }

        btnMiercoles.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.pecho,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosMiercoles
                )
            }
        }

        btnJueves.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.espalda,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosJueves
                )
            }
        }

        btnViernes.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.piernas,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosViernes
                )
            }
        }

        btnSabado.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.hombros,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosSabado
                )
            }
        }

        btnDomingo.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.abdomen,
                    textViews,
                    descripciones,
                    switches,
                    youtubePlayerView,
                    videosDomingo
                )
            }
        }
    }

    private fun setupNavigationDrawer() {
        binding.btnMenu.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupLogoutButton() {
        binding.btnLogout.setOnClickListener {
            // Limpiar la sesión
            sessionManager.clearSession()
            
            // Navegar al fragmento de login
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

        val tvEjercicios = listOf(
            binding.tvEjercicio1,
            binding.tvEjercicio2,
            binding.tvEjercicio3,
            binding.tvEjercicio4
        )

        chkBoxes.forEachIndexed { index, chkBox ->
            chkBox.setOnCheckedChangeListener { _, isChecked ->
                val tvEjercicio = tvEjercicios[index]
                if (isChecked) {
                    tvEjercicio.setBackgroundResource(R.drawable.rounded_background_gray)
                } else {
                    tvEjercicio.setBackgroundResource(R.drawable.rounded_background)
                }
            }
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
            // Limpiar la sesión
            sessionManager.clearSession()
            
            // Navegar al fragmento de login
            findNavController().navigate(R.id.action_user_to_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mis_datos -> {
                findNavController().navigate(R.id.action_user_to_userdata)
            }
            R.id.nav_progreso -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rutinas -> {
                Toast.makeText(context, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dieta -> {
                findNavController().navigate(R.id.action_user_to_recipes)
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
            R.id.nav_clima -> {
                findNavController().navigate(R.id.action_user_to_weather)
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
        youtubePlayerView.release()
        _binding = null
    }
}