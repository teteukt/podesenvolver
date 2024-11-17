package br.com.podesenvolver.presentation.podcastDetail

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

    init {
        getPodcastFromCache()
    }

    private fun getPodcastFromCache() {
        _event.value = Event.Loading

        viewModelScope.launch {
            try {
                localPodcastRepository.getSelectedPodcast()?.let {
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
