package br.com.podesenvolver.data.local.mapper

import br.com.podesenvolver.data.local.entity.PodcastWithEpisodesEntity
import br.com.podesenvolver.domain.Podcast

fun PodcastWithEpisodesEntity.toDomain(): Podcast =
    Podcast(
        title = this.podcast.title,
        imageUrl = this.podcast.imageUrl,
        author = this.podcast.author,
        description = this.podcast.description,
        category = this.podcast.category,
        episodes = this.episodes.map { it.toDomain() },
        cacheId = this.podcast.id
    )
