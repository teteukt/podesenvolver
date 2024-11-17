package br.com.podesenvolver.presentation.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeUI(episodeId: String, viewModel: EpisodeViewModel = koinViewModel()) {
    LaunchedEffect(null) {
        viewModel.fetchEpisodeDataById(episodeId)
    }

    DisposableEffect(LocalContext.current) {
        onDispose {
            viewModel.releasePlayer()
        }
    }
}
