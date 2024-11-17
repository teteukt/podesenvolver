package br.com.podesenvolver.data.local.repository

import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast

interface LocalPodcastRepository {
    fun selectPodcast(podcast: Podcast)
    fun getSelectedPodcast(): Podcast?
    fun getEpisodeFromSelectedPodcastById(episodeId: String): Episode?
}