package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.runtime.mutableStateOf
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RSSFeedViewModel(
    private val podcastRepository: PodcastRepository,
    private val localPodcastRepository: LocalPodcastRepository
) : BaseViewModel() {

    val rssUrlText = mutableStateOf("")
    val actionError = mutableStateOf<ActionError?>(null)

    private val _event = MutableStateFlow<Event>(Event.Initial)
    val event: StateFlow<Event> = _event

    fun fetchPodcast() {
        _event.value = Event.Loading
        val rssPodcastUrl = rssUrlText.value

        launch(::handleGetPodcastError) {
            val podcast = podcastRepository.getPodcast(rssPodcastUrl)
            val podcastId = localPodcastRepository.savePodcast(podcast)
            _event.value = Event.RedirectToPodcast(podcastId)
        }
    }

    private fun handleGetPodcastError(throwable: Throwable) {
        _event.value = when (throwable) {
            is IllegalArgumentException -> {
                actionError.value = ActionError.Parse
                Event.ParseError
            }
            else -> {
                actionError.value = ActionError.Generic
                Event.GenericError
            }
        }
    }

    fun isLoading() = event.value is Event.Loading

    enum class ActionError {
        Generic, Parse
    }

    sealed class Event {
        data object Initial : Event()
        data class RedirectToPodcast(val podcastId: Long) : Event()
        data object ParseError : Event()
        data object GenericError : Event()
        data object Loading : Event()
    }
}
