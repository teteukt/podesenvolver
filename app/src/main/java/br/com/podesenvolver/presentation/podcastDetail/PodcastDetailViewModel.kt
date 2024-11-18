package br.com.podesenvolver.presentation.podcastDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastDetailViewModel(private val localPodcastRepository: LocalPodcastRepository) : ViewModel() {

    private val _state = MutableStateFlow<Event>(Event.Loading)
    val state: StateFlow<Event> = _state

    fun getPodcastById(id: Long) {
        _state.value = Event.Loading

        viewModelScope.launch {
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
