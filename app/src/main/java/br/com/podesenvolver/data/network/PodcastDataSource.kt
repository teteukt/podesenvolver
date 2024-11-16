package br.com.podesenvolver.data.network

import br.com.podesenvolver.data.network.entity.PodcastRssResponse
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.flow

class PodcastDataSource(private val podcastApi: PodcastApi) {
    fun getPodcast(url: String) = flow {
        val response = podcastApi.getPodcastRss(url)
        emit(response)
    }
}