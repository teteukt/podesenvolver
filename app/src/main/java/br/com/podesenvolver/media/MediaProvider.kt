package br.com.podesenvolver.media

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer

class MediaProvider {
    companion object {
        fun createExoPlayer(application: Application) = ExoPlayer.Builder(application).build()
    }
}