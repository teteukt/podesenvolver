package br.com.podesenvolver.data.network.repository

import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.Flow

interface PodcastRepository {
    suspend fun getPodcast(url: String): Flow<Podcast?>
}