package br.com.podesenvolver.data.network.repository

import br.com.podesenvolver.domain.Podcast

interface PodcastRepository {
    suspend fun getPodcast(url: String): Podcast?
}