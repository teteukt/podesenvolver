package br.com.podesenvolver.presentation.podcastDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.podesenvolver.presentation.EXTRA_PODCAST_URL
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.intentEpisode
import org.koin.androidx.viewmodel.ext.android.viewModel

class PodcastDetailActivity : ComponentActivity() {

    private val podcastId: Long? by lazy { intent?.extras?.getLong(EXTRA_PODCAST_URL) }
    private val viewModel: PodcastDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val safePodcastId = podcastId
        if (safePodcastId == null) {
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsState()
            PodesenvolverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        PodcastDetailUI(state, onClickEpisode = {
                            startActivity(intentEpisode(it.id))
                        })
                    }
                }
            }
        }

        viewModel.getPodcastById(safePodcastId)
    }
}
