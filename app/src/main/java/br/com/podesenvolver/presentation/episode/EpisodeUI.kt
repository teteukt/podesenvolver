package br.com.podesenvolver.presentation.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeUI(episodeId: String, viewModel: EpisodeViewModel = koinViewModel()) {
    LaunchedEffect("init") {
        viewModel.fetchEpisodeDataById(episodeId)
    }
}
