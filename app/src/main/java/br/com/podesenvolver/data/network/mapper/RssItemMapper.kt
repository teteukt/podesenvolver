package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.extensions.isLong
import br.com.podesenvolver.extensions.toTimeDisplayText
import tw.ktrssreader.kotlin.model.item.ITunesItemData

fun ITunesItemData.toDomain(index: Int): Episode {

    val duration = this.duration.orEmpty()

    val formattedDuration = when {
        duration.isLong() -> (duration.toLong() * 1000).toTimeDisplayText()
        else -> duration
    }

    return Episode(
        index = index,
        id = 0,
        guid = this.guid?.value.orEmpty(),
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        enclosure = this.enclosure?.toDomain() ?: EMPTY_ENCLOSURE,
        duration = formattedDuration
    )
}
