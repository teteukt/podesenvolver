package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.ui.theme.PodesenvolverTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PodcastUI(url: String?, onDetectInvalidUrl: () -> Unit, viewModel: PodcastViewModel = koinViewModel()) {
    val state: PodcastViewModel.Event by viewModel.event.collectAsState()

    LaunchedEffect("init") {
        url?.let { url ->
            viewModel.fetchPodcast(url)
        } ?: run {
            onDetectInvalidUrl()
        }
    }

    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                when(val screenState = state) {
                    is PodcastViewModel.Event.WithPodcastData -> UIWithPodcast(screenState.podcast)
                    else -> UILoading()
                }
            }
        }
    }
}

@Composable
private fun UIWithPodcast(podcast: Podcast) {
    Column {
        Text(podcast.title)
        Text(podcast.imageUrl)
        Text(podcast.author)
        Text(podcast.description)
        Text(podcast.category)
    }
}

@Composable
private fun UILoading() {
    CircularProgressIndicator()
}