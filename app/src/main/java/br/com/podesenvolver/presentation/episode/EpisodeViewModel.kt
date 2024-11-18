package br.com.podesenvolver.presentation.episode

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val repository: LocalPodcastRepository,
    private val exoPlayer: ExoPlayer
) : BaseViewModel() {

    private val _state = MutableStateFlow<Event>(Event.Loading)
    val state: StateFlow<Event> = _state

    private val _playing = MutableStateFlow(false)
    val playing: StateFlow<Boolean> = _playing

    fun getEpisodeById(id: Long) {
        _state.value = Event.Loading

        launch {
            repository.getEpisodeById(id)?.let {
                prepareEpisode(it)
                playEpisode()
                _state.value = Event.WithEpisode(it)
            } ?: run {
                _state.value = Event.NotFound
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

    fun playEpisode() {
        if (_playing.value.not()) {
            exoPlayer.play()
            _playing.value = true
        }
    }

    fun pauseEpisode() {
        if (_playing.value) {
            exoPlayer.pause()
            _playing.value = false
        }
    }

    fun seekEpisodeTo(progress: Float) {
        exoPlayer.seekTo((exoPlayer.duration.toFloat() * progress).toLong())
    }

    sealed class Event {
        data object Loading : Event()
        data object NotFound : Event()
        data class WithEpisode(val episode: Episode) : Event()
    }
}
