package br.com.podesenvolver.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.podesenvolver.data.local.entity.EpisodeEntity

@Dao
interface EpisodeDao {

    @Insert
    suspend fun insertAll(episodes: List<EpisodeEntity>)

    @Query("SELECT * FROM Episode WHERE id=:episodeId")
    suspend fun getById(episodeId: Long): EpisodeEntity?
}
