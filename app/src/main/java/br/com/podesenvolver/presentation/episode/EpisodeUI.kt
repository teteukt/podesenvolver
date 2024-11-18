package br.com.podesenvolver.presentation.episode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.podesenvolver.R
import br.com.podesenvolver.presentation.PodesenvolverTheme
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

@Composable
fun PlayerControls(
    playing: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onGoPrevious: () -> Unit,
    onGoNext: () -> Unit,
    onSeek: (progress: Float) -> Unit
) {
    var sliderValue by remember { mutableFloatStateOf(0F) }

    Column {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            onValueChangeFinished = {
                onSeek(sliderValue)
            }
        )

        Row {
            IconButton(onClick = onGoPrevious) {
                Image(
                    painterResource(R.drawable.round_skip_previous_24),
                    "faixa anterior"
                )
            }
            IconButton(onClick = {
                if (playing) {
                    onPause()
                } else {
                    onPlay()
                }
            }) {
                if (playing) {
                    Image(painterResource(R.drawable.round_pause_circle_24), "pausar")
                } else {
                    Image(painterResource(R.drawable.round_play_circle_24), "reproduzir")
                }
            }
            IconButton(onClick = onGoNext) {
                Image(
                    painterResource(R.drawable.round_skip_next_24),
                    "próxima faixa"
                )
            }
        }
    }
}

@Composable
private fun UILoading() {
    CircularProgressIndicator()
}
