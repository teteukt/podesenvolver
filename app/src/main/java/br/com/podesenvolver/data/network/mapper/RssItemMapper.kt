package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.domain.Episode
import tw.ktrssreader.kotlin.model.item.ITunesItemData

fun ITunesItemData.toDomain() = Episode(
    id = this.guid?.value.orEmpty(),
    title = this.title.orEmpty(),
    description = this.description.orEmpty()
)
