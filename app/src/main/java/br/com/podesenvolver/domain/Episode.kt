package br.com.podesenvolver.domain

data class Episode(
    val index: Int,
    val id: String,
    val title: String,
    val description: String,
    val enclosure: EpisodeEnclosure
)
