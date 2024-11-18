package br.com.podesenvolver.media

import android.app.Application
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer

class MediaProvider {
    companion object {
        @OptIn(UnstableApi::class)
        fun createExoPlayer(application: Application): ExoPlayer {
            val loadControl = DefaultLoadControl.Builder()
                .build()
            return ExoPlayer.Builder(application).setLoadControl(loadControl).build()
        }
    }
}
