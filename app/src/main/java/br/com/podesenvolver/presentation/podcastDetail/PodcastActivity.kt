package br.com.podesenvolver.presentation.podcastDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class PodcastActivity : ComponentActivity() {

    private val rssPodcastUrl: String? by lazy { intent?.extras?.getString("EXTRA_URL") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PodcastUI(rssPodcastUrl, { finish() })
        }
    }
}