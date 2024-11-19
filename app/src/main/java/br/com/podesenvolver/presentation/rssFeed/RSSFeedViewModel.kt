package br.com.podesenvolver.presentation.rssFeed

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import br.com.podesenvolver.R

class RSSFeedViewModel(
    private val podcastRepository: PodcastRepository,
    private val localPodcastRepository: LocalPodcastRepository
) : BaseViewModel() {

    private val _foundPodcast = MutableLiveData<Long?>()
    val foundPodcast: LiveData<Long?> = _foundPodcast

    val error = mutableStateOf<ErrorType?>(null)

    private val _state = MutableStateFlow<State>(State.Initial)
    val state: StateFlow<State> = _state

    fun fetchPodcast(rssPodcastUrl: String) {
        _state.value = State.Loading

        launch(::handleGetPodcastError) {
            val podcast = podcastRepository.getPodcast(rssPodcastUrl)
            val podcastId = localPodcastRepository.savePodcast(podcast)
            _foundPodcast.postValue(podcastId)
        }
    }

    private fun handleGetPodcastError(throwable: Throwable) {
        _state.value = when (throwable) {
            is IllegalArgumentException -> {
                error.value = ErrorType.NotFound
                State.Initial
            }
            else -> {
                error.value = ErrorType.Generic
                State.Initial
            }
        }
    }

    enum class ErrorType(
        @StringRes val title: Int,
        @StringRes val description: Int
    ) {
        Generic(
            title = R.string.rss_feed_screen_title_generic_error,
            description = R.string.rss_feed_screen_message_generic_error
        ), NotFound(
            title = R.string.rss_feed_screen_title_not_found_error,
            description = R.string.rss_feed_screen_message_not_found_error
        )
    }

    sealed class State {
        data object Initial : State()
        data object Loading : State()
    }
}
