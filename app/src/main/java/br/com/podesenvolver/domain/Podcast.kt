package br.com.podesenvolver.domain

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

data class Podcast(
    val title: String,
    val episodes: List<Episode>,
    val imageUrl: String,
    val author: String,
    val description: String,
    val category: String
)