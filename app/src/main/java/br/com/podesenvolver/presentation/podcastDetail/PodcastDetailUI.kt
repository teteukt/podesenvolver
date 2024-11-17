package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.intentEpisode
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun PodcastDetailUI(viewModel: PodcastDetailViewModel = koinViewModel()) {
    val state: PodcastDetailViewModel.Event by viewModel.event.collectAsState()

    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                when (val screenState = state) {
                    is PodcastDetailViewModel.Event.WithPodcastData -> UIWithPodcast(screenState.podcast) {
                        viewModel.selectEpisode(it)
                    }
                    is PodcastDetailViewModel.Event.SelectedEpisode -> StartEpisodeActivity(screenState.episode)
                    else -> UILoading()
                }
            }
        }
    }
}

@Composable
private fun UIWithPodcast(podcast: Podcast, onClickEpisode: (Episode) -> Unit) {
    LazyColumn {
        item {
            AsyncImage(podcast.imageUrl, "")
        }
        item {
            Text(podcast.title)
        }

        item {
            Text(podcast.author)
        }

        item {
            Text(podcast.description)
        }

        item {
            Text(podcast.category)
        }

        item {
            Text("Episódios")
        }

        items(podcast.episodes) {
            Column(
                Modifier.clickable {
                    onClickEpisode(it)
                }
            ) {
                Text(it.title)
                Text(AnnotatedString.fromHtml(it.description))
            }
        }
    }
}

@Composable
private fun StartEpisodeActivity(episode: Episode) {
    with(LocalContext.current) {
        startActivity(intentEpisode(episode.id))
    }
}

@Composable
private fun UILoading() {
    CircularProgressIndicator()
}
