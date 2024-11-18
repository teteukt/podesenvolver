package br.com.podesenvolver.presentation.podcastDetail.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import br.com.podesenvolver.domain.Episode

@Composable
fun UIPodcastEpisodeItem(episode: Episode, onClick: (Episode) -> Unit) {
    Column(
        Modifier.clickable {
            onClick(episode)
        }
    ) {
        Text("${episode.index + 1}. ${episode.title}")
        Text(AnnotatedString.fromHtml(episode.description), maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}