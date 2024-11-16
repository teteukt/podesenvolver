package br.com.podesenvolver.data.network.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "item")
data class EpisodeResponse(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "description")
    var description: String? = null,
)