package br.com.podesenvolver.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.podesenvolver.data.local.entity.PodcastEntity

@Dao
interface PodcastDao {

    @Insert
    suspend fun insert(podcast: PodcastEntity): Long

    @Transaction
    @Query("DELETE FROM Podcast WHERE id=:podcastId")
    suspend fun deleteById(podcastId: Long)
}
