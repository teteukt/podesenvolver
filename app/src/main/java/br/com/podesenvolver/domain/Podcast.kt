package br.com.podesenvolver.domain

data class Podcast(
    val title: String,
    val episodes: List<Episode>,
    val imageUrl: String,
    val author: String,
    val description: String,
    val category: String,
    val cacheId: Long = 0,
    val rssUrl: String
)
