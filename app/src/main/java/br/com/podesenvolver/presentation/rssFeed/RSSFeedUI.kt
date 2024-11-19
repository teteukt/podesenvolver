package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.podesenvolver.R

@Composable
fun RSSFeedUI(
    rssUrlText: String,
    onChangeRssUrlText: (url: String) -> Unit,
    onSearch: (rssPodcastUrl: String) -> Unit,
    searchButtonEnabled: Boolean
) {
    Column {
        TextField(value = rssUrlText, onValueChange = onChangeRssUrlText)
        Button(onClick = {
            onSearch(rssUrlText)
        }, enabled = searchButtonEnabled) { Text(stringResource(R.string.rss_feed_screen_search_button)) }
    }
}