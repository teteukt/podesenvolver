package br.com.podesenvolver.presentation.podcastDetail.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.EpisodeEnclosure
import br.com.podesenvolver.presentation.podcastDetail.ui.components.UIPodcastEpisodeItem

@Preview
@Composable
fun PreviewUIPodcastEpisodeItem() {
    UIPodcastEpisodeItem(
        Episode(
            index = 0,
            id = 0,
            guid = "",
            title = "Título do episódio",
            description = "Lorem <b>ipsum>/b> dolor sit amet, consectetur adipiscing elit. Vestibulum in facilisis libero. Donec cursus nunc diam, vitae finibus nunc cursus eu. Curabitur bibendum arcu turpis, nec placerat quam pulvinar ut",
            enclosure = EpisodeEnclosure(""),
            duration = "00:00:00"
        )
    ) {}
}
