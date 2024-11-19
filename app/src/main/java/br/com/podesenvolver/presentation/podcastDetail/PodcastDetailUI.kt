package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.podesenvolver.R
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
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
        Column(Modifier.padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(podcast.title)
            AsyncImage(
                podcast.imageUrl,
                "imagem do podcast",
                modifier = Modifier.heightIn(max = 128.dp)
            )
            Text(podcast.author)
            Text(podcast.description)
            Text(podcast.category)
            Text(stringResource(R.string.podcast_detail_episodes))
        }
        LazyColumn(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(podcast.episodes) {
                UIPodcastEpisodeItem(episode = it, onClick = onClickEpisode)
            }
        }
    }
}
