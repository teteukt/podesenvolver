package br.com.podesenvolver.data.network.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class ChannelResponse(

    @field:Element(name = "title")
    val title: String,

    @field:ElementList(name = "item", inline = true)
    val episodes: List<EpisodeResponse>
)