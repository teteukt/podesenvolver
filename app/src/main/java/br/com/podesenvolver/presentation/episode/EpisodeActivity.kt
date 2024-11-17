package br.com.podesenvolver.presentation.episode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.podesenvolver.presentation.EXTRA_EPISODE_ID
import br.com.podesenvolver.presentation.EXTRA_PODCAST_URL

class EpisodeActivity : ComponentActivity() {

    private val episodeId: String? by lazy { intent?.extras?.getString(EXTRA_EPISODE_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val safeEpisodeId = episodeId

        if(safeEpisodeId == null) {
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            EpisodeUI(safeEpisodeId)
        }

    }
}
