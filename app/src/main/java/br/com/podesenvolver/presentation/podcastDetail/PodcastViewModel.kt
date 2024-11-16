package br.com.podesenvolver.presentation.podcastDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.presentation.rssFeed.RSSFeedViewModel.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastViewModel(private val podcastRepository: PodcastRepository): ViewModel() {

    private val _event = MutableStateFlow<Event>(Event.Loading)
    val event: StateFlow<Event> = _event

    fun fetchPodcast(url: String) {
        _event.value = Event.Loading

        viewModelScope.launch {
            try {
                _event.value = Event.WithPodcastData(podcastRepository.getPodcast(url))
            } catch(error: Throwable) {
                _event.value = Event.Error
            }
        }
    }

    fun selectEpisode(episode: Episode) {
        _event.value = Event.SelectedEpisode(episode)
    }

    sealed class Event {
        data object Loading: Event()
        data object Error: Event()
        data class WithPodcastData(val podcast: Podcast): Event()
        data class SelectedEpisode(val episode: Episode): Event()
    }
}