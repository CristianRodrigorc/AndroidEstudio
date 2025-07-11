package com.cristian.appgym.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.cristian.appgym.model.model_ejercicio.Ejercicio
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("StaticFieldLeak")
object UtilidadesUsersScreen {
    private var lastVisibleDesc: TextView? = null
    private var youTubePlayer: YouTubePlayer? = null
    private var currentVideoId: String? = null

    fun llenarDiaGenerico(
        ejerciciosDelDia: List<Ejercicio>,
        textViews: List<TextView>,
        repeticiones: List<TextView>,
        checkboxes: List<CheckBox>,
        youtubeView: YouTubePlayerView,
        videoIds: List<String>
    ) {
        textViews.forEachIndexed { index, tvEjercicio ->
            val tvRepeticion = repeticiones.getOrNull(index)
            val checkbox = checkboxes[index]
            val videoId = videoIds.getOrNull(index) ?: ""

            ejerciciosDelDia.getOrNull(index)?.let { ejercicio ->
                tvEjercicio.text = ejercicio.nombre
                tvRepeticion?.text = ejercicio.repeticiones
            }

            tvRepeticion?.visibility = View.GONE
            checkbox.isChecked = false

            tvEjercicio.setOnClickListener {
                tvRepeticion?.let { verDesc(it) }
                cargarVideo(videoId)
            }
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
            try {
                youTubePlayer?.loadVideo(videoId, 0f)
                currentVideoId = videoId
            } catch (e: Exception) {
                // Manejar errores al cargar el video
                e.printStackTrace()
            }
        }
    }

    fun cargarVideo(youtubeView: YouTubePlayerView, id: String) {
        currentVideoId = id
        youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(player: YouTubePlayer) {
                youTubePlayer = player
                try {
                    player.loadVideo(id, 0f)
                    player.mute()
                } catch (e: Exception) {
                    // Manejar errores al cargar el video
                    e.printStackTrace()
                }
            }

            override fun onError(player: YouTubePlayer, error: PlayerConstants.PlayerError) {
                // Manejar errores del reproductor
                youTubePlayer = null
            }
        })
    }
}
