package br.com.podesenvolver.presentation.episode.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.EpisodeEnclosure
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.episode.EpisodeViewModel
import br.com.podesenvolver.presentation.episode.UIWithEpisode

@Preview(showBackground = true)
@Composable
fun PreviewUIWithEpisode() {
    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                UIWithEpisode(
                    positionDuration = PositionDuration(15000, 60000),
                    state = EpisodeViewModel.State.WithEpisode(
                        episode = Episode(
                            index = 0,
                            id = 0,
                            guid = "",
                            title = "Título do episódio",
                            description = "descrição",
                            enclosure = EpisodeEnclosure(""),
                            duration = ""
                        ),
                        playing = true,
                        duration = 60000,
                        imageUrl = ""
                    ),
                    onPlay = { },
                    onPause = { },
                    onGoPrevious = {},
                    onGoNext = {},
                    onSeek = {}
                )
            }
        }
    }
}
