package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.data.network.entity.PodcastRssResponse
import br.com.podesenvolver.domain.Podcast

fun PodcastRssResponse.toDomain(): Podcast = Podcast(
    episodes = this.channel.episodes.map { it.toDomain() }
)