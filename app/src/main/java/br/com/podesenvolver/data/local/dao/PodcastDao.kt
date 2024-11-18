package br.com.podesenvolver.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.podesenvolver.data.local.entity.PodcastEntity

@Dao
interface PodcastDao {

    @Insert
    suspend fun insert(podcast: PodcastEntity): Long
}
