package br.com.podesenvolver.presentation.episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.extensions.toTimeDisplayText
import br.com.podesenvolver.presentation.episode.ui.components.PlayerControls
import br.com.podesenvolver.presentation.ui.components.UIError
import br.com.podesenvolver.presentation.ui.components.UILoading

@Composable
fun EpisodeUI(
    positionDuration: PositionDuration,
    state: EpisodeViewModel.State,
    onPlay: (Episode) -> Unit,
    onPause: () -> Unit,
    onSeek: (Float) -> Unit,
    onGoPrevious: () -> Unit,
    onGoNext: () -> Unit
) {
    when (state) {
        is EpisodeViewModel.State.WithEpisode -> UIWithEpisode(
            positionDuration = positionDuration,
            state = state,
            onPlay = onPlay,
            onPause = onPause,
            onGoPrevious = onGoPrevious,
            onGoNext = onGoNext,
            onSeek = onSeek
        )

        is EpisodeViewModel.State.Loading -> UILoading()
        is EpisodeViewModel.State.NotFound -> UIError(
            "Não encontrado",
            "O episódio requisitado não pôde ser encontrado ou não existe."
        )
    }
}

@Composable
fun UIWithEpisode(
    positionDuration: PositionDuration,
    state: EpisodeViewModel.State.WithEpisode,
    onPlay: (Episode) -> Unit,
    onPause: () -> Unit,
    onSeek: (Float) -> Unit,
    onGoPrevious: () -> Unit,
    onGoNext: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.episode.title)
        TimerDurationText(positionDuration)
        PlayerControls(
            positionDuration = positionDuration,
            playing = state.playing,
            onPlay = { onPlay(state.episode) },
            onPause = onPause,
            onGoPrevious = onGoPrevious,
            onGoNext = onGoNext,
            onSeek = onSeek
        )
    }
}

@Composable
fun TimerDurationText(
    positionDuration: PositionDuration
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(positionDuration.position.toTimeDisplayText())
        Text(positionDuration.duration.toTimeDisplayText())
    }
}
