package br.com.podesenvolver.data.network.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class PodcastRssResponse {

    @field:Element(name = "title")
    @field:Path("channel")
    var title: String? = null

    @field:ElementList(inline = true, entry = "item")
    @field:Path("channel")
    var episodes: List<EpisodeResponse>? = null
}