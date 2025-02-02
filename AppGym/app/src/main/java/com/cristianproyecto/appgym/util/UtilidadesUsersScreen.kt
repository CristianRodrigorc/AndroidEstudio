package com.cristianproyecto.appgym.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.cristianproyecto.appgym.model.Ejercicios
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("StaticFieldLeak")
object UtilidadesUsersScreen{
    private var lastVisibleDesc: TextView? = null
    private var youTubePlayer: YouTubePlayer? = null

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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.biceps

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "3k0Iu_ogVtw"),
            Triple(tvEjercicio2, tvDescripcion2, "oq42eVowbD4"),
            Triple(tvEjercicio3, tvDescripcion3, "lG53BKnQlxY"),
            Triple(tvEjercicio4, tvDescripcion4, "Is3JRhq37o4")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.triceps

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "aLtLLvffF6M"),
            Triple(tvEjercicio2, tvDescripcion2, "vTdr4UKscRE"),
            Triple(tvEjercicio3, tvDescripcion3, "gY-CqZD0Ktc"),
            Triple(tvEjercicio4, tvDescripcion4, "uDjXYcXR0ys")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
        }
    }



    fun llenarDiaMiercoles(
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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.pecho

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "8LR0mo8iD8s"),
            Triple(tvEjercicio2, tvDescripcion2, "vw_pcm2ly5Y"),
            Triple(tvEjercicio3, tvDescripcion3, "ItBASjwB_Wo"),
            Triple(tvEjercicio4, tvDescripcion4, "SnNyF9g8dDE")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
        }
    }

    fun llenarDiaJueves(
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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.espalda

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "Vg1nmlJzGgM"),
            Triple(tvEjercicio2, tvDescripcion2, "31vdmfx5pJs"),
            Triple(tvEjercicio3, tvDescripcion3, "JddDALFiRbw"),
            Triple(tvEjercicio4, tvDescripcion4, "SCsH7Z7qDwU")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
        }
    }


    fun llenarDiaViernes(
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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.piernas

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "ozCH5r2lP2E"),
            Triple(tvEjercicio2, tvDescripcion2, "MetkFq2hMZs"),
            Triple(tvEjercicio3, tvDescripcion3, "FyWvCXvCC-w"),
            Triple(tvEjercicio4, tvDescripcion4, "Pe9fw_B-B34")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
        }
    }


    fun llenarDiaSabado(
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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.hombros

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "Xaa6rn3Hrh4"),
            Triple(tvEjercicio2, tvDescripcion2, "DAMw-xGYNck"),
            Triple(tvEjercicio3, tvDescripcion3, "9V8eNF-fkls"),
            Triple(tvEjercicio4, tvDescripcion4, "bheSKL7AhGY")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
        }
    }


    fun llenarDiaDomingo(
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
    ) {
        inicializarYoutubePlayer(youtubeView)

        val ejerciciosBiceps = ejercicios.ejercicios.abdomen

        listOf(
            Triple(tvEjercicio1, tvDescripcion1, "QMvyEWQrmsY"),
            Triple(tvEjercicio2, tvDescripcion2, "2ypB_CmVILM"),
            Triple(tvEjercicio3, tvDescripcion3, "P60uxBkcaNI"),
            Triple(tvEjercicio4, tvDescripcion4, "rMznoDrT5aI")
        ).forEachIndexed { index, (tvEjercicio, tvDescripcion, videoId) ->
            ejerciciosBiceps.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }
            tvDescripcion.visibility = View.GONE // Oculta las descripciones por defecto
            listOf(switch, switch2, switch3, switch4)[index].isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId)
            }
        }
    }

    private fun inicializarYoutubePlayer(youtubeView: YouTubePlayerView) {
        if (youTubePlayer == null) {
            youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(player: YouTubePlayer) {
                    youTubePlayer = player
                }
            })
        }
    }

    private fun verDesc(descView: TextView) {
        if (lastVisibleDesc != null && lastVisibleDesc != descView) {
            lastVisibleDesc?.visibility = View.GONE
        }


        if (descView.visibility == View.GONE) {
            descView.visibility = View.VISIBLE
            lastVisibleDesc = descView
        } else {
            descView.visibility = View.GONE
            lastVisibleDesc = null
        }
    }

    private fun cargarVideo(videoId: String) {
        youTubePlayer?.loadVideo(videoId, 0f)
    }

    fun cargarVideo(youtubeView: YouTubePlayerView, id: String){
        youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                //youTubePlayer.mute()
                youTubePlayer.loadVideo(id, 0f)
            }
        })
    }
}