package br.com.podesenvolver.presentation.rssFeed

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.network.repository.PodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RSSFeedViewModel(private val podcastRepository: PodcastRepository) : ViewModel() {

    val rssUrlText = mutableStateOf("")
    val actionError = mutableStateOf<ActionError?>(null)

    private val _event = MutableStateFlow<Event>(Event.Initial)
    val event: StateFlow<Event> = _event

    fun fetchPodcast() {
        _event.value = Event.Loading
        val rssPodcastUrl = rssUrlText.value

        viewModelScope.launch {
            try {
                podcastRepository.getPodcast(rssPodcastUrl)
                _event.value = Event.RedirectToPodcast(rssPodcastUrl)
            } catch (error: Throwable) {
                handleGetPodcastError(error)
            }
        }
    }

    private fun handleGetPodcastError(throwable: Throwable) {
        Log.e("Podesenvolver", throwable.message, throwable)
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
        data class RedirectToPodcast(val url: String) : Event()
        data object ParseError : Event()
        data object GenericError : Event()
        data object Loading : Event()
    }
}
