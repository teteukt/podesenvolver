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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.intentPodcastDetail
import br.com.podesenvolver.presentation.rssFeed.ui.components.AlertDialogActionError
import org.koin.androidx.viewmodel.ext.android.viewModel

class RSSFeedActivity : ComponentActivity() {

    private val viewModel: RSSFeedViewModel by viewModel()

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
            val fetchingPodcast by remember { viewModel.fetchingPodcast }
            val lastPodcastsState by viewModel.lastPodcasts.collectAsState()

            FetchErrorDialogAlert()

            PodesenvolverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(Modifier.padding(it)) {
                        RSSFeedUI(
                            fetchingPodcast = fetchingPodcast,
                            onSearch = { rssUrl -> viewModel.fetchPodcast(rssUrl) },
                            lastPodcastsState = lastPodcastsState,
                            onClearPodcastFromHistory = { viewModel.deletePodcastFromHistory(it) },
                            onClickPodcastInHistory = { viewModel.fetchPodcast(it.rssUrl) }
                        )
                    }
                }
            }
        }

        viewModel.foundPodcast.observe(this@RSSFeedActivity) { podcastId ->
            podcastId?.let { startPodcastDetailActivity(podcastId) }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchLastPodcasts()
    }

    private fun startPodcastDetailActivity(podcastId: Long) {
        startActivity(intentPodcastDetail(podcastId))
    }
}
