package br.com.podesenvolver.presentation.episode.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import br.com.podesenvolver.R

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