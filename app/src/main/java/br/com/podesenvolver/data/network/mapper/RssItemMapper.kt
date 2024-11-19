package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.domain.Episode
import tw.ktrssreader.kotlin.model.item.ITunesItemData

fun ITunesItemData.toDomain(index: Int) = Episode(
    index = index,
    id = 0,
    guid = this.guid?.value.orEmpty(),
    title = this.title.orEmpty(),
    description = this.description.orEmpty(),
    enclosure = this.enclosure?.toDomain() ?: EMPTY_ENCLOSURE,
    duration = this.duration.orEmpty()
)
