package br.com.podesenvolver.presentation.episode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.podesenvolver.presentation.EXTRA_EPISODE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeActivity : ComponentActivity() {

    private val episodeId: Long? by lazy { intent?.extras?.getLong(EXTRA_EPISODE_ID) }
    private val viewModel: EpisodeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val safeEpisodeId = episodeId

        if (safeEpisodeId == null) {
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            EpisodeUI()
        }

        viewModel.getEpisodeById(safeEpisodeId)
    }
}
