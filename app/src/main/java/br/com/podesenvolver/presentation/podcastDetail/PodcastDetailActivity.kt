package br.com.podesenvolver.presentation.podcastDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.podesenvolver.presentation.EXTRA_PODCAST_URL

class PodcastDetailActivity : ComponentActivity() {

    private val podcastId: Long? by lazy { intent?.extras?.getLong(EXTRA_PODCAST_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val safePodcastId = podcastId
        if (safePodcastId == null) {
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            PodcastDetailUI(safePodcastId)
        }
    }
}
