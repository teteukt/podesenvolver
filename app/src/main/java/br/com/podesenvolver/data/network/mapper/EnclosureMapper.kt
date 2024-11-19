package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.domain.EpisodeEnclosure
import tw.ktrssreader.kotlin.model.item.Enclosure

val EMPTY_ENCLOSURE = EpisodeEnclosure("")

fun Enclosure.toDomain() = EpisodeEnclosure(
    audioUrl = this.url.orEmpty()
)
