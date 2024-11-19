package br.com.podesenvolver.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.podesenvolver.data.local.dao.EpisodeDao
import br.com.podesenvolver.data.local.dao.PodcastDao
import br.com.podesenvolver.data.local.dao.PodcastWithEpisodesDao
import br.com.podesenvolver.data.local.entity.EpisodeEntity
import br.com.podesenvolver.data.local.entity.PodcastEntity

@Database(entities = [PodcastEntity::class, EpisodeEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPodcastDataAccessObject(): PodcastDao
    abstract fun getEpisodeDataAccessObject(): EpisodeDao
    abstract fun getPodcastWithEpisodesDataAccessObject(): PodcastWithEpisodesDao
}
