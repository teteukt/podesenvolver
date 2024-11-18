package br.com.podesenvolver.data.local.repository

import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast

interface LocalPodcastRepository {
    suspend fun savePodcast(podcast: Podcast): Long
    suspend fun getRecentPodcasts(): List<Podcast>
    suspend fun getPodcastById(id: Long): Podcast?
    suspend fun getEpisodeById(episodeId: Long): Episode?
}
