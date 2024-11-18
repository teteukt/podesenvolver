package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.intentPodcastDetail
import org.koin.androidx.compose.koinViewModel

@Composable
fun RSSFeedUI(
    viewModel: RSSFeedViewModel = koinViewModel()
) {
    var rssUrlText by remember { viewModel.rssUrlText }
    var alertError by remember { viewModel.actionError }
    val state by viewModel.event.collectAsState()

    @Composable
    fun handleActionErrorAlert() {
        alertError?.let { error ->
            val title = when (error) {
                RSSFeedViewModel.ActionError.Generic -> "Erro desconhecido"
                RSSFeedViewModel.ActionError.Parse -> "Conteúdo desconhecido"
            }

            val text = when (error) {
                RSSFeedViewModel.ActionError.Generic -> "Aconteceu um erro não previsto. Revise a URL e tente novamente."
                RSSFeedViewModel.ActionError.Parse -> "O conteúdo entregue pela URL digitada não pôde ser lido corretamente. Revise a URL e tente novamente."
            }

            AlertDialogActionError(onDismiss = { alertError = null }, title = title, text = text)
        }
    }

    (state as? RSSFeedViewModel.Event.RedirectToPodcast)?.let {
        StartPodcastActivity(it.podcastId)
    }

    PodesenvolverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            handleActionErrorAlert()
            Column(modifier = Modifier.padding(innerPadding)) {
                TextField(value = rssUrlText, onValueChange = { rssUrlText = it })
                Button(onClick = {
                    viewModel.fetchPodcast()
                }, enabled = viewModel.isLoading().not()) { Text("Buscar") }
            }
        }
    }
}

@Composable
private fun AlertDialogActionError(onDismiss: () -> Unit, title: String, text: String) {
    AlertDialog(onDismissRequest = onDismiss, confirmButton = {
        Button(onClick = onDismiss) { Text("Fechar") }
    }, title = { Text(title) }, text = { Text(text) })
}

@Composable
private fun StartPodcastActivity(podcastId: Long) {
    with(LocalContext.current) {
        startActivity(intentPodcastDetail(podcastId))
    }
}
