package br.com.podesenvolver.domain

import br.com.podesenvolver.extensions.toTimeDisplayText

data class Podcast(
    val title: String,
    val episodes: List<Episode>,
    val imageUrl: String,
    val author: String,
    val description: String,
    val category: String,
    val cacheId: Long = 0,
    val rssUrl: String
) {
    fun getTotalDuration() = this.episodes.sumOf { it.getDurationInLong() }.toTimeDisplayText()
}
