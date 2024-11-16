package br.com.podesenvolver.data.network.repository

interface PodcastRepository {
    suspend fun getPodcastRssEpisodes()
}