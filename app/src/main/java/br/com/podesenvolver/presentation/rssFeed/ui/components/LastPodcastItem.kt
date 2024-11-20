package br.com.podesenvolver.presentation.rssFeed.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.podesenvolver.R
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.SPACING_SMALL
import coil3.compose.AsyncImage

@Composable
fun LastPodcastItem(podcast: Podcast, onClickClear: (Podcast) -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            podcast.imageUrl,
            "",
            modifier = Modifier
                .height(32.dp)
                .padding(SPACING_SMALL)
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            podcast.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.weight(1F))
        IconButton({ onClickClear(podcast) }) {
            Icon(
                painterResource(R.drawable.round_clear_24),
                "excluir histórico",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
