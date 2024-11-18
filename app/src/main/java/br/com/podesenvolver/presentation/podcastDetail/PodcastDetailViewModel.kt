package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastDetailViewModel(private val localPodcastRepository: LocalPodcastRepository) : ViewModel() {

    private val _event = MutableStateFlow<Event>(Event.Loading)
    val event: StateFlow<Event> = _event

    fun getPodcastById(id: Long) {
        _event.value = Event.Loading

        viewModelScope.launch {
            try {
                localPodcastRepository.getPodcastById(id)?.let {
                    _event.value = Event.WithPodcastData(it)
                }
            } catch (_: Throwable) {
                _event.value = Event.Error
            }
        }
    }

    sealed class Event {
        data object Loading : Event()
        data object Error : Event()
        data class WithPodcastData(val podcast: Podcast) : Event()
    }
}
