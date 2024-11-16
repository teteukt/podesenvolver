package br.com.podesenvolver.data.network.repository

import br.com.podesenvolver.data.network.PodcastApi
import br.com.podesenvolver.data.network.mapper.toDomain
import br.com.podesenvolver.domain.Podcast

class PodcastRepositoryImpl(private val podcastApi: PodcastApi) : PodcastRepository {
    override suspend fun getPodcast(url: String): Podcast? =
        podcastApi.getPodcastRss(url)?.toDomain()
}