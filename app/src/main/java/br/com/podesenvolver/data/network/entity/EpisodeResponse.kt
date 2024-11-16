package br.com.podesenvolver.data.network.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class EpisodeResponse(
    @Element(name = "itunes:title")
    val title: String
)