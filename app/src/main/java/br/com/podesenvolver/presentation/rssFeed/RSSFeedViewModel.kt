package br.com.podesenvolver.presentation.rssFeed

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.podesenvolver.R
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.xml.sax.SAXParseException
import java.net.ConnectException

class RSSFeedViewModel(
    private val podcastRepository: PodcastRepository,
    private val localPodcastRepository: LocalPodcastRepository
) : BaseViewModel() {

    private val _foundPodcast = MutableLiveData<Long?>()
    val foundPodcast: LiveData<Long?> = _foundPodcast

    val error = mutableStateOf<ErrorType?>(null)
    val fetchingPodcast = mutableStateOf(false)

    private val _lastPodcasts = MutableStateFlow<LastPodcastState>(LastPodcastState.Loading)
    val lastPodcasts: StateFlow<LastPodcastState> = _lastPodcasts

    fun fetchLastPodcasts() {
        _lastPodcasts.value = LastPodcastState.Loading
        launch(errorBlock = { _lastPodcasts.value = LastPodcastState.Empty }) {
            _lastPodcasts.value =
                localPodcastRepository.getRecentPodcasts().takeIf { it.isNotEmpty() }?.let {
                    LastPodcastState.WithPodcasts(it)
                } ?: run { LastPodcastState.Empty }
        }
    }

    fun selectPodcastFromHistory(podcast: Podcast) {
        _foundPodcast.postValue(podcast.cacheId)
    }

    fun fetchPodcast(rssPodcastUrl: String) {
        fetchingPodcast.value = true

        launch(::handleGetPodcastError) {
            val podcast = podcastRepository.getPodcast(rssPodcastUrl)
            val podcastId = localPodcastRepository.savePodcast(podcast)
            _foundPodcast.postValue(podcastId)
            fetchingPodcast.value = false
        }
    }

    fun deletePodcastFromHistory(podcast: Podcast) {
        launch {
            localPodcastRepository.deletePodcastById(podcast.cacheId)
            fetchLastPodcasts()
        }
    }

    private fun handleGetPodcastError(throwable: Throwable) {
        error.value = when (throwable) {
            is SAXParseException, is ConnectException -> ErrorType.InvalidRss
            else -> ErrorType.Generic
        }

        fetchingPodcast.value = false
    }

    enum class ErrorType(
        @StringRes val title: Int,
        @StringRes val description: Int
    ) {
        Generic(
            title = R.string.rss_feed_screen_title_generic_error,
            description = R.string.rss_feed_screen_message_generic_error
        ),
        InvalidRss(
            title = R.string.rss_feed_screen_title_invalid_rss_error,
            description = R.string.rss_feed_screen_message_invalid_rss_error
        )
    }

    sealed class LastPodcastState {
        data object Loading : LastPodcastState()
        data object Empty : LastPodcastState()
        data class WithPodcasts(val podcasts: List<Podcast>) : LastPodcastState()
    }
}
