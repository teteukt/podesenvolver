package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.podesenvolver.presentation.ui.theme.PodesenvolverTheme

@Composable
fun PodcastUI() {
    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}