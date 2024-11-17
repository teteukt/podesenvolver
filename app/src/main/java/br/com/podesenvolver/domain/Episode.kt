package br.com.podesenvolver.domain

data class Episode(
    val id: String,
    val title: String,
    val description: String,
    val enclosure: EpisodeEnclosure
)
