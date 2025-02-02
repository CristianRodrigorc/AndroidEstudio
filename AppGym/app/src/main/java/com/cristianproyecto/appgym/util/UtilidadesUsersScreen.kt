package com.cristianproyecto.appgym.util

import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.cristianproyecto.appgym.model.Ejercicios
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

object UtilidadesUsersScreen {

    fun llenarDiaLunes(
        ejercicios: Ejercicios,
        tvEjercicio1: TextView,
        tvDescripcion1: TextView,
        switch: Switch,
        tvEjercicio2: TextView,
        tvDescripcion2: TextView,
        switch2: Switch,
        tvEjercicio3: TextView,
        tvDescripcion3: TextView,
        switch3: Switch,
        tvEjercicio4: TextView,
        tvDescripcion4: TextView,
        switch4: Switch,
        youtubeView: YouTubePlayerView,
    ){
        ejercicios?.ejercicios?.biceps?.get(0).let {
            if(it != null){
                tvEjercicio1.text = it.nombre
                tvDescripcion1.text = it.repeticiones
            }
            switch.isChecked = false
        }
        ejercicios?.ejercicios?.biceps?.get(1).let {
            if(it != null){
                tvEjercicio2.text = it.nombre
                tvDescripcion2.text = it.repeticiones
            }
            switch2.isChecked = false
        }
        ejercicios?.ejercicios?.biceps?.get(2).let {
            if(it != null){
                tvEjercicio3.text = it.nombre
                tvDescripcion3.text = it.repeticiones
            }
            switch3.isChecked = false
        }
        ejercicios?.ejercicios?.biceps?.get(3).let {
            if(it != null){
                tvEjercicio4.text = it.nombre
                tvDescripcion4.text = it.repeticiones
            }
            switch4.isChecked = false
        }

        tvEjercicio1.setOnClickListener {
            verDesc(tvDescripcion1)
            val id = "JGoQA1ItmpM"
            verVideo(youtubeView,id)

        }
        tvEjercicio2.setOnClickListener {
            verDesc(tvDescripcion2)
            val id = "0ajYejJTFVw"
            verVideo(youtubeView,id)
        }
        tvEjercicio3.setOnClickListener {
            verDesc(tvDescripcion3)
            val id = "Wc9HsGgQ6_w"
            verVideo(youtubeView,id)
        }
        tvEjercicio4.setOnClickListener {
            verDesc(tvDescripcion4)
            val id = "Is3JRhq37o4"
            verVideo(youtubeView,id)
        }

    }

    fun llenarDiaMartes(
        ejercicios: Ejercicios,
        tvEjercicio1: TextView,
        tvDescripcion1: TextView,
        switch: Switch,
        tvEjercicio2: TextView,
        tvDescripcion2: TextView,
        switch2: Switch,
        tvEjercicio3: TextView,
        tvDescripcion3: TextView,
        switch3: Switch,
        tvEjercicio4: TextView,
        tvDescripcion4: TextView,
        switch4: Switch,
        youtubeView: YouTubePlayerView,
    ){
        ejercicios?.ejercicios?.biceps?.get(0).let {
            if(it != null){
                tvEjercicio1.text = it.nombre
                tvDescripcion1.text = it.repeticiones
            }
            switch.isChecked = false
        }
        ejercicios?.ejercicios?.biceps?.get(1).let {
            if(it != null){
                tvEjercicio2.text = it.nombre
                tvDescripcion2.text = it.repeticiones
            }
            switch2.isChecked = false
        }
        ejercicios?.ejercicios?.biceps?.get(2).let {
            if(it != null){
                tvEjercicio3.text = it.nombre
                tvDescripcion3.text = it.repeticiones
            }
            switch3.isChecked = false
        }
        ejercicios?.ejercicios?.biceps?.get(3).let {
            if(it != null){
                tvEjercicio4.text = it.nombre
                tvDescripcion4.text = it.repeticiones
            }
            switch4.isChecked = false
        }
    }

    fun llenarDiaMiercoles(){

    }
    fun llenarDiaJueves(){

    }
    fun llenarDiaViernes(){

    }
    fun llenarDiaSabado(){

    }
    fun llenarDiaDomingo(){

    }

    private fun verDesc(descView: TextView) {
        if (descView.visibility == View.GONE) {
            descView.visibility = View.VISIBLE
        } else {
            descView.visibility = View.GONE
        }
    }

    fun verVideo(youtubeView: YouTubePlayerView, id: String){
        youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                //youTubePlayer.mute()
                youTubePlayer.loadVideo(id, 0f)
            }
        })
    }
}