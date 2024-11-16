package br.com.podesenvolver.data.network.repository

import br.com.podesenvolver.data.network.PodcastDataSource
import br.com.podesenvolver.data.network.mapper.toDomain
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PodcastRepositoryImpl(private val podcastDataSource: PodcastDataSource) : PodcastRepository {
    override suspend fun getPodcast(url: String): Podcast =
        podcastDataSource.getPodcast(url).toDomain()
}