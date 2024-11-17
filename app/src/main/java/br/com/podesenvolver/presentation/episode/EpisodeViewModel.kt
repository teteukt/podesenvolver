package br.com.podesenvolver.presentation.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import kotlinx.coroutines.launch

class EpisodeViewModel(private val repository: LocalPodcastRepository) : ViewModel() {
    private val _episode = MutableLiveData<Event>(Event.Loading)
    val episode: LiveData<Event> = _episode

    fun fetchEpisodeDataById(id: String) {
        _episode.value = Event.Loading

        viewModelScope.launch {
            repository.getEpisodeFromSelectedPodcastById(id)?.let {
                _episode.value = Event.WithEpisode(it)
            } ?: run {
                _episode.value = Event.NotFound
            }
        }

    }

    sealed class Event {
        data object Loading : Event()
        data object NotFound : Event()
        data class WithEpisode(val episode: Episode) : Event()
    }
}