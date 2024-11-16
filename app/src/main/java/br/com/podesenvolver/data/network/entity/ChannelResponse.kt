package br.com.podesenvolver.data.network.entity

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class ChannelResponse(

    @ElementList(name = "item", required = false)
    val episodes: List<EpisodeResponse>
)