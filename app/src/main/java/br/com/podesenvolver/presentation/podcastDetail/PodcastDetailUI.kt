package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.podesenvolver.R
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.SPACING_DEFAULT
import br.com.podesenvolver.presentation.SPACING_MEDIUM
import br.com.podesenvolver.presentation.SPACING_SMALL
import br.com.podesenvolver.presentation.Typography
import br.com.podesenvolver.presentation.podcastDetail.ui.components.UIPodcastEpisodeItem
import br.com.podesenvolver.presentation.ui.components.UIError
import br.com.podesenvolver.presentation.ui.components.UILoading
import coil3.compose.AsyncImage

@Composable
fun PodcastDetailUI(state: PodcastDetailViewModel.Event, onClickEpisode: (Episode) -> Unit) {
    when (state) {
        is PodcastDetailViewModel.Event.WithPodcastData -> UIWithPodcast(
            state.podcast,
            onClickEpisode = onClickEpisode
        )

        is PodcastDetailViewModel.Event.Loading -> UILoading()
        is PodcastDetailViewModel.Event.Error -> UIError(
            "Vish!",
            "Ocorreu um erro desconhecido!"
        )
    }
}

@Composable
private fun UIWithPodcast(podcast: Podcast, onClickEpisode: (Episode) -> Unit) {
    Column {
        Column(
            Modifier.padding(horizontal = SPACING_MEDIUM),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(podcast.title, style = Typography.titleLarge)
            Text(podcast.author)
            Spacer(Modifier.padding(vertical = SPACING_DEFAULT))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                AsyncImage(
                    podcast.imageUrl,
                    "imagem do podcast",
                    modifier = Modifier.widthIn(max = 128.dp).clip(RoundedCornerShape(4.dp))
                )
                Spacer(Modifier.padding(horizontal = SPACING_SMALL))
                Text(podcast.description, style = Typography.bodySmall)
            }
            Spacer(Modifier.padding(vertical = SPACING_SMALL))
            Text(podcast.category, style = Typography.bodySmall)
            Text(stringResource(R.string.podcast_detail_episodes), style = Typography.titleMedium)
        }

        Spacer(Modifier.padding(vertical = SPACING_SMALL))

        LazyColumn {
            items(podcast.episodes) {
                UIPodcastEpisodeItem(episode = it, onClick = onClickEpisode)
            }
        }
    }
}
