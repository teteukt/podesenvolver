package br.com.podesenvolver.data.local.repository

import br.com.podesenvolver.data.local.AppDatabase
import br.com.podesenvolver.data.local.LocalPodcastDataSource
import br.com.podesenvolver.data.local.entity.EpisodeEntity
import br.com.podesenvolver.data.local.entity.PodcastEntity
import br.com.podesenvolver.data.local.mapper.toDomain
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast

class LocalPodcastRepositoryImpl(
    private val localPodcastDataSource: LocalPodcastDataSource,
    private val appDatabase: AppDatabase
) :
    LocalPodcastRepository {

    override fun selectPodcast(podcast: Podcast) {
        localPodcastDataSource.selectPodcast(podcast)
    }

    override fun getSelectedPodcast() = localPodcastDataSource.selectedPodcast
    override fun getEpisodeFromSelectedPodcastById(episodeId: String): Episode? =
        localPodcastDataSource.getEpisodeFromSelectedPodcastById(episodeId)

    override suspend fun savePodcast(podcast: Podcast): Long {
        val podcastEntity = PodcastEntity(
            id = 0,
            title = podcast.title,
            imageUrl = podcast.imageUrl,
            author = podcast.author,
            description = podcast.description,
            category = podcast.category
        )

        val podcastEntityId = appDatabase.getPodcastDataAccessObject().insert(podcastEntity)

        val episodeEntities = podcast.episodes.map {
            EpisodeEntity(
                id = it.id,
                index = it.index,
                guid = it.guid,
                podcastId = podcastEntityId,
                title = it.title,
                description = it.description,
                audioUrl = it.enclosure.audioUrl,
                type = it.enclosure.type,
                length = it.enclosure.length
            )
        }

        appDatabase.getEpisodeDataAccessObject().insertAll(episodeEntities)
        return podcastEntityId
    }

    override suspend fun getRecentPodcasts() =
        appDatabase.getPodcastWithEpisodesDataAccessObject().getRecent().map { it.toDomain() }

    override suspend fun getPodcastById(id: Long): Podcast? =
        appDatabase.getPodcastWithEpisodesDataAccessObject().getById(id)?.toDomain()

    override suspend fun getEpisodeById(episodeId: Long): Episode? =
        appDatabase.getEpisodeDataAccessObject().getById(episodeId)?.toDomain()
}
