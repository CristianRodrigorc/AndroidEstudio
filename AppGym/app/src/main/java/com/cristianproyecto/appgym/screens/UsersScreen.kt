package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.LectorJSON
import com.cristianproyecto.appgym.util.UtilidadesBotones
import com.cristianproyecto.appgym.util.UtilidadesUsersScreen
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class UsersScreen : AppCompatActivity() {
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

        if (ejerciciosJson != null) {
            UtilidadesUsersScreen.llenarDiaLunes(
                ejerciciosJson,
                tvEjercicio1,
                descEjercicio1,
                swtEjercicio1,
                tvEjercicio2,
                descEjercicio2,
                swtEjercicio2,
                tvEjercicio3,
                descEjercicio3,
                swtEjercicio3,
                tvEjercicio4,
                descEjercicio4,
                swtEjercicio4,
                youtubeView)
        }

        UtilidadesUsersScreen.cargarVideo(youtubeView,idVideoYTDefault)

        btnLunes.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaLunes(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        btnMartes.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaMartes(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        btnMiercoles.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaMiercoles(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        btnJueves.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaJueves(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        btnViernes.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaViernes(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        btnSabado.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaSabado(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        btnDomingo.setOnClickListener {
            if (ejerciciosJson != null) {
                UtilidadesUsersScreen.llenarDiaDomingo(
                    ejerciciosJson,
                    tvEjercicio1,
                    descEjercicio1,
                    swtEjercicio1,
                    tvEjercicio2,
                    descEjercicio2,
                    swtEjercicio2,
                    tvEjercicio3,
                    descEjercicio3,
                    swtEjercicio3,
                    tvEjercicio4,
                    descEjercicio4,
                    swtEjercicio4,
                    youtubeView)
            }
        }

        UtilidadesBotones.cambiarScreen(btnVolverUserS,this,MainActivity::class.java)
    }
}
