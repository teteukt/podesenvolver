package br.com.podesenvolver.presentation.podcastDetail.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.presentation.Typography

@Composable
fun UIPodcastEpisodeItem(episode: Episode, onClick: (Episode) -> Unit) {
    ListItem(headlineContent = {
        Column(
            Modifier.clickable {
                onClick(episode)
            }
        ) {
            Text("${episode.index + 1}. ${episode.title}", style = Typography.titleMedium)
            Text(
                AnnotatedString.fromHtml(
                    htmlString = episode.description,
                    linkInteractionListener = { onClick(episode) },
                    linkStyles = TextLinkStyles(
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    )
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = Typography.bodySmall
            )
        }
    })
}
