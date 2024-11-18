package br.com.podesenvolver.presentation.episode

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.episode.ui.components.PlayerControls
import br.com.podesenvolver.presentation.ui.components.UILoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeUI(
    viewModel: EpisodeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                when (currentState) {
                    is EpisodeViewModel.Event.WithEpisode -> UIWithEpisode(
                        event = currentState,
                        onGoPrevious = {},
                        onGoNext = {}
                    )

                    else -> UILoading()
                }
            }
        }
    }
}

@Composable
fun UIWithEpisode(
    event: EpisodeViewModel.Event.WithEpisode,
    onGoPrevious: () -> Unit,
    onGoNext: () -> Unit,
    viewModel: EpisodeViewModel = koinViewModel()
) {
    val playing by viewModel.playing.collectAsState()

    Column {
        Text(event.episode.title)
        PlayerControls(
            playing = playing,
            onPlay = { viewModel.playEpisode() },
            onPause = { viewModel.pauseEpisode() },
            onGoPrevious = onGoPrevious,
            onGoNext = onGoNext,
            onSeek = viewModel::seekEpisodeTo
        )
    }
}