package br.com.podesenvolver.presentation.episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.presentation.SPACING_MEDIUM
import br.com.podesenvolver.presentation.episode.ui.components.PlayerControls
import br.com.podesenvolver.presentation.episode.ui.components.TimerDurationText
import br.com.podesenvolver.presentation.ui.components.UIError
import br.com.podesenvolver.presentation.ui.components.UILoading
import coil3.compose.AsyncImage

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
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = SPACING_MEDIUM),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            state.imageUrl,
            contentDescription = "imagem do episódio",
            modifier = Modifier.heightIn(max = 512.dp).clip(RoundedCornerShape(8.dp))

        )
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
