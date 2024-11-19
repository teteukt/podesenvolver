package br.com.podesenvolver.presentation.rssFeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.intentPodcastDetail
import br.com.podesenvolver.presentation.rssFeed.ui.components.AlertDialogActionError
import org.koin.androidx.viewmodel.ext.android.viewModel

class RSSFeedActivity : ComponentActivity() {

    val viewModel: RSSFeedViewModel by viewModel()

    @Composable
    private fun FetchErrorDialogAlert() {
        var error by remember { viewModel.error }

        error?.let {
            AlertDialogActionError(
                onDismiss = { error = null },
                title = stringResource(it.title),
                text = stringResource(it.description)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsState()
            var rssUrlText by remember { mutableStateOf("") }
            var searchButtonEnabled by remember { mutableStateOf(true) }

            LaunchedEffect(rssUrlText, state) {
                searchButtonEnabled =
                    rssUrlText.isNotBlank() || (state is RSSFeedViewModel.State.Loading).not()
            }

            FetchErrorDialogAlert()

            PodesenvolverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(Modifier.padding(it)) {
                        RSSFeedUI(
                            onSearch = { viewModel.fetchPodcast(rssUrlText) },
                            searchButtonEnabled = searchButtonEnabled,
                            rssUrlText = rssUrlText,
                            onChangeRssUrlText = { rssUrlText = it }
                        )
                    }
                }
            }
        }

        viewModel.foundPodcast.observe(this@RSSFeedActivity) { podcastId ->
            podcastId?.let { startPodcastDetailActivity(podcastId) }
        }
    }

    private fun startPodcastDetailActivity(podcastId: Long) {
        startActivity(intentPodcastDetail(podcastId))
    }
}
