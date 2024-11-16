package br.com.podesenvolver.domain

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

data class Podcast(
    val episodes: List<Episode>
)