package com.cristianproyecto.appgym.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.cristianproyecto.appgym.model.Ejercicio
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("StaticFieldLeak")
object UtilidadesUsersScreen {
    private var lastVisibleDesc: TextView? = null
    private var youTubePlayer: YouTubePlayer? = null
    private var currentVideoId: String? = null  // Nuevo: para evitar recargar el mismo video

    fun llenarDiaGenerico(
        ejerciciosDelDia: List<Ejercicio>,
        textViews: List<TextView>,
        descripciones: List<TextView>,
        switches: List<Switch>,
        youtubeView: YouTubePlayerView,
        videoIds: List<String>
    ) {
        inicializarYoutubePlayer(youtubeView) // Asegura que solo se inicialice una vez

        textViews.forEachIndexed { index, tvEjercicio ->
            val tvDescripcion = descripciones[index]
            val switch = switches[index]
            val videoId = videoIds.getOrNull(index) ?: ""

            ejerciciosDelDia.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvDescripcion.text = ejercicio.repeticiones
            }

            tvDescripcion.visibility = View.GONE
            switch.isChecked = false

            tvEjercicio.setOnClickListener {
                verDesc(tvDescripcion)
                cargarVideo(videoId) // Ahora debería cargar correctamente el video
            }
        }
    }

    private fun inicializarYoutubePlayer(youtubeView: YouTubePlayerView) {
        if (youTubePlayer == null) {  // Solo se añade el listener si no está inicializado
            youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(player: YouTubePlayer) {
                    youTubePlayer = player
                    currentVideoId?.let { player.loadVideo(it, 0f) }
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
        if (videoId.isNotEmpty() && videoId != currentVideoId) {
            youTubePlayer?.loadVideo(videoId, 0f)
            currentVideoId = videoId
        }
    }


    fun cargarVideo(youtubeView: YouTubePlayerView, id: String) {
        currentVideoId = id
        inicializarYoutubePlayer(youtubeView)
        youTubePlayer?.loadVideo(id, 0f)
    }
}
