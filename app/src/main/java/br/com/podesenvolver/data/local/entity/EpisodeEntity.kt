package br.com.podesenvolver.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Episode",
    foreignKeys = [
        ForeignKey(
            entity = PodcastEntity::class,
            parentColumns = ["id"],
            childColumns = ["podcast_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "index") val index: Int,
    @ColumnInfo(name = "guid") val guid: String,
    @ColumnInfo(name = "podcast_id") val podcastId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "audio_url") val audioUrl: String,
    @ColumnInfo(name = "duration") val duration: String
)
