package br.com.podesenvolver.data.network.repository

import br.com.podesenvolver.data.network.PodcastApi

class PodcastRepositoryImpl(private val podcastApi: PodcastApi) : PodcastRepository {
    override suspend fun getPodcastRssEpisodes() {
        TODO("Not yet implemented")
    }
}