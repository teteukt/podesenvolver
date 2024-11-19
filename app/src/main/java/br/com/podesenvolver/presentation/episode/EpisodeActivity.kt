package br.com.podesenvolver.presentation.episode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.podesenvolver.presentation.EXTRA_EPISODE_ID
import br.com.podesenvolver.presentation.PodesenvolverTheme
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
            val state by viewModel.state.collectAsState()
            val position by viewModel.position.collectAsState()

            PodesenvolverTheme {
                PodesenvolverTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(Modifier.padding(innerPadding)) {
                            EpisodeUI(
                                position = position,
                                state = state,
                                onPlay = { viewModel.playEpisode(it) },
                                onPause = { viewModel.pauseEpisode() },
                                onGoNext = {},
                                onGoPrevious = {},
                                onSeek = { viewModel.seekEpisodeTo(it) })
                        }
                    }
                }
            }

            viewModel.getEpisodeById(safeEpisodeId)
        }
    }
}
