package br.com.podesenvolver.presentation.podcastDetail

import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PodcastDetailViewModel(private val localPodcastRepository: LocalPodcastRepository) : BaseViewModel() {

    private val _state = MutableStateFlow<Event>(Event.Loading)
    val state: StateFlow<Event> = _state

    fun getPodcastById(id: Long) {
        _state.value = Event.Loading

        launch {
            try {
                localPodcastRepository.getPodcastById(id)?.let {
                    _state.value = Event.WithPodcastData(it)
                }
            } catch (_: Throwable) {
                _state.value = Event.Error
            }
        }
    }

    sealed class Event {
        data object Loading : Event()
        data object Error : Event()
        data class WithPodcastData(val podcast: Podcast) : Event()
    }
}
