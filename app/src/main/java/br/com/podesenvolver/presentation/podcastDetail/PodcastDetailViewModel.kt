package br.com.podesenvolver.presentation.podcastDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.launch

class PodcastDetailViewModel(private val localPodcastRepository: LocalPodcastRepository) : ViewModel() {

    private val _event = mutableStateOf<Event>(Event.Loading)
    val event: State<Event> = _event

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

    fun selectEpisode(episode: Episode) {
        _event.value = Event.SelectedEpisode(episode)
    }

    sealed class Event {
        data object Loading : Event()
        data object Error : Event()
        data class WithPodcastData(val podcast: Podcast) : Event()
        data class SelectedEpisode(val episode: Episode) : Event()
    }
}
