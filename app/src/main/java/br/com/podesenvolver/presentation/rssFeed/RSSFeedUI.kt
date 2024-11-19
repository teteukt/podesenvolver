package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.R
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.SPACING_DEFAULT
import br.com.podesenvolver.presentation.SPACING_MEDIUM
import br.com.podesenvolver.presentation.Typography

@Composable
fun RSSFeedUI(
    fetchingPodcast: Boolean,
    onSearch: (rssPodcastUrl: String) -> Unit,
) {
    var rssUrlText by remember { mutableStateOf("") }
    var searchButtonEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(rssUrlText, fetchingPodcast) {
        searchButtonEnabled =
            rssUrlText.isNotBlank() || fetchingPodcast.not()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = SPACING_MEDIUM),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRSSFeedUI() {
    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Box(Modifier.padding(it)) {
                RSSFeedUI(
                    fetchingPodcast = false,
                    onSearch = { },
                )
            }
        }
    }
}