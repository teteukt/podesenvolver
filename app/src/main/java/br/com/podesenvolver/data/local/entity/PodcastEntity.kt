package br.com.podesenvolver.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Podcast")
data class PodcastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "rssUrl") val rssUrl: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
