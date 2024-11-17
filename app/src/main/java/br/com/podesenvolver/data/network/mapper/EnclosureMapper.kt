package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.domain.EpisodeEnclosure
import br.com.podesenvolver.extensions.orZero
import tw.ktrssreader.kotlin.model.item.Enclosure

val EMPTY_ENCLOSURE = EpisodeEnclosure("", "", 0)

fun Enclosure.toDomain() = EpisodeEnclosure(
    audioUrl = this.url.orEmpty(),
    type = this.type.orEmpty(),
    length = this.length.orZero()
)
