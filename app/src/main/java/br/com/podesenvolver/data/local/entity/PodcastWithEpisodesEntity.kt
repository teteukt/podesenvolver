package br.com.podesenvolver.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PodcastWithEpisodesEntity(
    @Embedded val podcast: PodcastEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "podcast_id"
    )
    val episodes: List<EpisodeEntity>
)
