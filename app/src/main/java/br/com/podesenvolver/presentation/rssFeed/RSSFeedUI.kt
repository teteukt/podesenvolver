package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.podesenvolver.R
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.SPACING_DEFAULT
import br.com.podesenvolver.presentation.SPACING_MEDIUM
import br.com.podesenvolver.presentation.SPACING_SMALL
import br.com.podesenvolver.presentation.Typography
import br.com.podesenvolver.presentation.rssFeed.ui.components.LastPodcastItem

@Composable
fun RSSFeedUI(
    fetchingPodcast: Boolean,
    onSearch: (rssPodcastUrl: String) -> Unit,
    lastPodcastsState: RSSFeedViewModel.LastPodcastState,
    onClearPodcastFromHistory: (Podcast) -> Unit,
    onClickPodcastInHistory: (Podcast) -> Unit
) {
    var rssUrlText by remember { mutableStateOf("") }
    var searchButtonEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(rssUrlText, fetchingPodcast) {
        searchButtonEnabled =
            rssUrlText.isNotBlank() && fetchingPodcast.not()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = SPACING_MEDIUM),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = SPACING_DEFAULT))
        Image(painterResource(R.drawable.podcast), "icone de microfone do podesenvolver")
        Spacer(Modifier.padding(vertical = SPACING_DEFAULT))
        Text(
            stringResource(R.string.app_name),
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text("Feed RSS para Podcasts Apple")
        Spacer(Modifier.padding(vertical = SPACING_DEFAULT))
        OutlinedTextField(
            enabled = fetchingPodcast.not(),
            value = rssUrlText,
            onValueChange = { rssUrlText = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                IconButton({ rssUrlText = "" }) {
                    Icon(
                        painterResource(R.drawable.round_clear_24),
                        "limpar campo de rss url"
                    )
                }
            }
        )
        Spacer(Modifier.padding(vertical = SPACING_DEFAULT))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSearch(rssUrlText)
            },
            enabled = searchButtonEnabled
        ) { Text(stringResource(R.string.rss_feed_screen_search_button)) }

        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            when (lastPodcastsState) {
                is RSSFeedViewModel.LastPodcastState.Loading -> LoadingLastPodcastList()
                is RSSFeedViewModel.LastPodcastState.WithPodcasts -> LastPodcastList(
                    lastPodcastsState.podcasts,
                    onClickPodcastInHistory,
                    onClearPodcastFromHistory
                )

                is RSSFeedViewModel.LastPodcastState.Empty -> EmptyLastPodcastList()
            }
        }
    }
}

@Composable
fun LoadingLastPodcastList() {
    Text(stringResource(R.string.rss_feed_screen_loading_last_podcasts))
}

@Composable
fun EmptyLastPodcastList() {
    Text(stringResource(R.string.rss_feed_screen_no_last_podcasts))
}

@Composable
fun LastPodcastList(
    podcasts: List<Podcast>,
    onClick: (Podcast) -> Unit,
    onClearPodcast: (Podcast) -> Unit
) {
    Text(stringResource(R.string.rss_feed_screen_last_podcasts), style = Typography.titleMedium)
    Spacer(Modifier.padding(vertical = SPACING_SMALL))
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(podcasts) {
            LastPodcastItem(it, onClick = onClick, onClickClear = onClearPodcast)
        }
    }
    Spacer(Modifier.padding(vertical = SPACING_DEFAULT))
}
