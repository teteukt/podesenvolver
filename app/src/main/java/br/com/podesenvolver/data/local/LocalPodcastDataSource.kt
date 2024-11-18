package br.com.podesenvolver.data.local

import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast

object LocalPodcastDataSource {
    var selectedPodcast: Podcast? = null
        private set

    fun selectPodcast(podcast: Podcast) {
        selectedPodcast = podcast
    }

    fun getEpisodeFromSelectedPodcastById(episodeId: String): Episode? = selectedPodcast?.episodes?.find { it.guid == episodeId }
}
