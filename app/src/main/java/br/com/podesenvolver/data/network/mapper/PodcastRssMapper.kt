package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.data.network.entity.PodcastRssResponse
import br.com.podesenvolver.domain.Podcast

fun PodcastRssResponse.toDomain(): Podcast = Podcast(
    title = this.title.orEmpty(),
    episodes = this.episodes?.map { it.toDomain() }.orEmpty()
)