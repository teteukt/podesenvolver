package br.com.podesenvolver.presentation.episode.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.R
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.presentation.PodesenvolverTheme

@Composable
fun PlayerControls(
    positionDuration: PositionDuration,
    playing: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onGoPrevious: () -> Unit,
    onGoNext: () -> Unit,
    onSeek: (progress: Float) -> Unit
) {
    var sliderValue by remember { mutableFloatStateOf(positionDuration.ratio()) }

    LaunchedEffect(positionDuration) {
        sliderValue = positionDuration.ratio()
    }

    Column(Modifier.fillMaxWidth()) {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderValue,
            onValueChange = { sliderValue = it },
            onValueChangeFinished = {
                onSeek(sliderValue)
            }
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(
                onClick = onGoPrevious
            ) {
                Image(
                    painter = painterResource(R.drawable.round_skip_previous_24),
                    contentDescription = "faixa anterior",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
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
                    Image(
                        painter = painterResource(R.drawable.round_pause_circle_24),
                        contentDescription = "pausar",
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.round_play_circle_24),
                        contentDescription = "reproduzir",
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                    )
                }
            }
            IconButton(onClick = onGoNext) {
                Image(
                    painter = painterResource(R.drawable.round_skip_next_24),
                    "próxima faixa",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerControls() {
    PodesenvolverTheme {
        PlayerControls(
            positionDuration = PositionDuration(30000, 60000),
            playing = false,
            onPlay = {},
            onPause = {},
            onGoPrevious = {},
            onGoNext = {},
            onSeek = {}
        )
    }
}
