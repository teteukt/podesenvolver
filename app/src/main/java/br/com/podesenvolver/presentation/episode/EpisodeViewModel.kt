package br.com.podesenvolver.presentation.episode

import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
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
        exoPlayer.stop()
    }

    fun fetchEpisodeDataById(id: String) {
        _episode.value = Event.Loading

        viewModelScope.launch {
            repository.getEpisodeFromSelectedPodcastById(id)?.let {
                prepareEpisode(it)
                playEpisode()
                _episode.value = Event.WithEpisode(it)
            } ?: run {
                _episode.value = Event.NotFound
            }
        }
    }

    @OptIn(UnstableApi::class)
    private fun prepareEpisode(episode: Episode) {
        val mediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(episode.enclosure.audioUrl))
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
    }

    private fun playEpisode() {
        exoPlayer.play()
    }

    sealed class Event {
        data object Loading : Event()
        data object NotFound : Event()
        data class WithEpisode(val episode: Episode) : Event()
    }
}