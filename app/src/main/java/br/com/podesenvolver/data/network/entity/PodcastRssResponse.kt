package br.com.podesenvolver.data.network.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class PodcastRssResponse(

    @Element(name = "channel")
    val channel: ChannelResponse
)