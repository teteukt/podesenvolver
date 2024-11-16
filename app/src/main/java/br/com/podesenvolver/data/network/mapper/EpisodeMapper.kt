package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.data.network.entity.EpisodeResponse
import br.com.podesenvolver.domain.Episode

fun EpisodeResponse.toDomain(): Episode = Episode(
    title = this.title
)