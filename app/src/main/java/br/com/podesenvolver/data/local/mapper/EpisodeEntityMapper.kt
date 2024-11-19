package br.com.podesenvolver.data.local.mapper

import br.com.podesenvolver.data.local.entity.EpisodeEntity
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.EpisodeEnclosure

fun EpisodeEntity.toDomain() = Episode(
    index = this.index,
    id = this.id,
    guid = this.guid,
    title = this.title,
    description = this.description,
    enclosure = EpisodeEnclosure(
        audioUrl = this.audioUrl,
        type = this.type,
        length = this.length
    ),
    duration = this.duration
)
