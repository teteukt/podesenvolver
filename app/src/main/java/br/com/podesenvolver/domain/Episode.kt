package br.com.podesenvolver.domain

data class Episode(
    val index: Int,
    val id: Long,
    val guid: String,
    val title: String,
    val description: String,
    val enclosure: EpisodeEnclosure,
    val duration: String
)
