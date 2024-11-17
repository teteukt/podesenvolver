package br.com.podesenvolver.presentation.podcastDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.podesenvolver.presentation.EXTRA_PODCAST_URL

class PodcastDetailActivity : ComponentActivity() {

    private val rssPodcastUrl: String? by lazy { intent?.extras?.getString(EXTRA_PODCAST_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PodcastDetailUI(rssPodcastUrl, { finish() })
        }
    }
}
