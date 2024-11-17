package br.com.podesenvolver.data.local.repository

import br.com.podesenvolver.data.local.LocalPodcastDataSource
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast

class LocalPodcastRepositoryImpl(private val localPodcastDataSource: LocalPodcastDataSource) :
    LocalPodcastRepository {

    override fun selectPodcast(podcast: Podcast) {
        localPodcastDataSource.selectPodcast(podcast)
    }

    override fun getSelectedPodcast() = localPodcastDataSource.selectedPodcast
    override fun getEpisodeFromSelectedPodcastById(episodeId: String): Episode? = localPodcastDataSource.getEpisodeFromSelectedPodcastById(episodeId)
}