package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.podesenvolver.presentation.ui.theme.PodesenvolverTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RSSFeedUI(viewModel: RSSFeedViewModel = koinViewModel()) {
    var rssUrlText by remember { viewModel.rssUrlText }
    val rssFeedUIState = viewModel.rssFeedUiState.collectAsState()

    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TextField(value = rssUrlText, onValueChange = { rssUrlText = it })
                Button(onClick = {
                    viewModel.fetchPodcast()
                }, enabled = viewModel.shouldEnableSearchButton()) { Text("Buscar") }

                (rssFeedUIState.value as? RSSFeedViewModel.RSSFeedUIState.Error)?.let {
                    Text(it.exception.message.orEmpty())
                }
            }
        }
    }
}