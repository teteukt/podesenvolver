package br.com.podesenvolver.presentation.rssFeed.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.rssFeed.RSSFeedUI
import br.com.podesenvolver.presentation.rssFeed.RSSFeedViewModel

@Preview(showSystemUi = true)
@Composable
fun PreviewRSSFeedUI() {
    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Box(Modifier.padding(it)) {
                RSSFeedUI(
                    fetchingPodcast = false,
                    onSearch = { },
                    onClearPodcastFromHistory = {},
                    lastPodcastsState = RSSFeedViewModel.LastPodcastState.Empty
                )
            }
        }
    }
}
