package br.com.podesenvolver.presentation.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val repository: LocalPodcastRepository,
    private val exoPlayer: ExoPlayer
) : ViewModel() {

    private val _episode = MutableStateFlow<Event>(Event.Loading)
    val episode: StateFlow<Event> = _episode

    fun releasePlayer() {
        exoPlayer.release()
    }

    fun fetchEpisodeDataById(id: String) {
        _episode.value = Event.Loading

        viewModelScope.launch {
            repository.getEpisodeFromSelectedPodcastById(id)?.let {
                exoPlayer.setMediaItem(MediaItem.fromUri(it.enclosure.audioUrl))
                exoPlayer.prepare()
                exoPlayer.play()
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