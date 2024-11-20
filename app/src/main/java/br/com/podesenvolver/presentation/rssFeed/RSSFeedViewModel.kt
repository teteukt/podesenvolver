package br.com.podesenvolver.presentation.rssFeed

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.podesenvolver.R
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.presentation.BaseViewModel
import org.xml.sax.SAXParseException

class RSSFeedViewModel(
    private val podcastRepository: PodcastRepository,
    private val localPodcastRepository: LocalPodcastRepository
) : BaseViewModel() {

    private val _foundPodcast = MutableLiveData<Long?>()
    val foundPodcast: LiveData<Long?> = _foundPodcast

    val error = mutableStateOf<ErrorType?>(null)
    val fetchingPodcast = mutableStateOf(false)

    fun fetchPodcast(rssPodcastUrl: String) {
        fetchingPodcast.value = true

        launch(::handleGetPodcastError) {
            val podcast = podcastRepository.getPodcast(rssPodcastUrl)
            val podcastId = localPodcastRepository.savePodcast(podcast)
            _foundPodcast.postValue(podcastId)
            fetchingPodcast.value = false
        }
    }

    private fun handleGetPodcastError(throwable: Throwable) {
        error.value = when (throwable) {
            is SAXParseException -> ErrorType.InvalidRss
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
}
