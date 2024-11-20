package br.com.podesenvolver.data.network.mapper

import br.com.podesenvolver.domain.Podcast
import tw.ktrssreader.kotlin.model.channel.ITunesChannelData

fun ITunesChannelData.toDomain(rssUrl: String) = Podcast(
    title = this.title.orEmpty(),
    imageUrl = this.image?.url.orEmpty(),
    author = this.author.orEmpty(),
    description = this.description.orEmpty(),
    category = this.categories.orEmpty().firstOrNull()?.name.orEmpty(),
    episodes = this.items?.mapIndexed { index, item -> item.toDomain(index) }.orEmpty(),
    rssUrl = rssUrl
)
