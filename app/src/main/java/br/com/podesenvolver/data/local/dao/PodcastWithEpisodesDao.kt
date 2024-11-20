package br.com.podesenvolver.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.podesenvolver.data.local.entity.PodcastWithEpisodesEntity

@Dao
interface PodcastWithEpisodesDao {

    @Transaction
    @Query("SELECT * FROM Podcast ORDER BY created_at DESC LIMIT 5")
    suspend fun getRecent(): List<PodcastWithEpisodesEntity>

    @Transaction
    @Query("SELECT * FROM Podcast WHERE id=:id")
    suspend fun getById(id: Long): PodcastWithEpisodesEntity?
}
