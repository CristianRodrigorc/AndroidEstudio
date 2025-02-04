package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.cristianproyecto.appgym.BaseActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.LectorJSON
import com.cristianproyecto.appgym.util.UtilidadesBotones
import com.cristianproyecto.appgym.util.UtilidadesUsersScreen
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class UsersScreen : BaseActivity() {
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users_screen)

        val btnLunes = findViewById<Button>(R.id.btnLunes)
        val btnMartes = findViewById<Button>(R.id.btnMartes)
        val btnMiercoles = findViewById<Button>(R.id.btnMiercoles)
        val btnJueves = findViewById<Button>(R.id.btnJueves)
        val btnViernes = findViewById<Button>(R.id.btnViernes)
        val btnSabado = findViewById<Button>(R.id.btnSabado)
        val btnDomingo = findViewById<Button>(R.id.btnDomingo)
        val btnVolverUserS = findViewById<Button>(R.id.btnVolverUserS)

        val tvEjercicio1 = findViewById<TextView>(R.id.tvEjercicio1)
        val swtEjercicio1 = findViewById<Switch>(R.id.swtEjercicio1)
        val descEjercicio1 = findViewById<TextView>(R.id.descEjercicio1)
        val tvEjercicio2 = findViewById<TextView>(R.id.tvEjercicio2)
        val swtEjercicio2 = findViewById<Switch>(R.id.swtEjercicio2)
        val descEjercicio2 = findViewById<TextView>(R.id.descEjercicio2)
        val tvEjercicio3 = findViewById<TextView>(R.id.tvEjercicio3)
        val swtEjercicio3 = findViewById<Switch>(R.id.swtEjercicio3)
        val descEjercicio3 = findViewById<TextView>(R.id.descEjercicio3)
        val tvEjercicio4 = findViewById<TextView>(R.id.tvEjercicio4)
        val swtEjercicio4 = findViewById<Switch>(R.id.swtEjercicio4)
        val descEjercicio4 = findViewById<TextView>(R.id.descEjercicio4)
        val youtubeView = findViewById<YouTubePlayerView>(R.id.youtubeView)
        lifecycle.addObserver(youtubeView)
        val ejerciciosJson = LectorJSON.obtenerJsonGson(this)
        val idVideoYTDefault = "40mFq3HDQso"
        UtilidadesUsersScreen.cargarVideo(youtubeView, idVideoYTDefault)

        val textViews = listOf(tvEjercicio1, tvEjercicio2, tvEjercicio3, tvEjercicio4)
        val descripciones = listOf(descEjercicio1, descEjercicio2, descEjercicio3, descEjercicio4)
        val switches = listOf(swtEjercicio1, swtEjercicio2, swtEjercicio3, swtEjercicio4)

        val videosPorDia = mapOf(
            "Lunes" to listOf("3k0Iu_ogVtw", "oq42eVowbD4", "lG53BKnQlxY", "Is3JRhq37o4"),
            "Martes" to listOf("aLtLLvffF6M", "vTdr4UKscRE", "gY-CqZD0Ktc", "uDjXYcXR0ys"),
            "Miercoles" to listOf("8LR0mo8iD8s", "vw_pcm2ly5Y", "ItBASjwB_Wo", "SnNyF9g8dDE"),
            "Jueves" to listOf("Vg1nmlJzGgM", "31vdmfx5pJs", "JddDALFiRbw", "SCsH7Z7qDwU"),
            "Viernes" to listOf("ozCH5r2lP2E", "MetkFq2hMZs", "FyWvCXvCC-w", "Pe9fw_B-B34"),
            "Sabado" to listOf("Xaa6rn3Hrh4", "DAMw-xGYNck", "9V8eNF-fkls", "bheSKL7AhGY"),
            "Domingo" to listOf("QMvyEWQrmsY", "2ypB_CmVILM", "P60uxBkcaNI", "rMznoDrT5aI")
        )

        btnLunes.setOnClickListener {
            ejerciciosJson?.let {
                UtilidadesUsersScreen.llenarDiaGenerico(
                    it.ejercicios.biceps,
                    textViews,
                    descripciones,
                    switches,
                    youtubeView,
                    videosPorDia["Lunes"]!!
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
                    youtubeView,
                    videosPorDia["Martes"]!!
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
                    youtubeView,
                    videosPorDia["Miercoles"]!!
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
                    youtubeView,
                    videosPorDia["Jueves"]!!
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
                    youtubeView,
                    videosPorDia["Viernes"]!!
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
                    youtubeView,
                    videosPorDia["Sabado"]!!
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
                    youtubeView,
                    videosPorDia["Domingo"]!!
                )
            }
        }

        UtilidadesBotones.cambiarScreen(btnVolverUserS,this,MainActivity::class.java)
    }
}
