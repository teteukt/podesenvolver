package br.com.podesenvolver.presentation.episode

import androidx.annotation.OptIn
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val repository: LocalPodcastRepository,
    private val exoPlayer: ExoPlayer
) : BaseViewModel() {

    private var positionJob: Job? = null

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state

    private val _position = MutableStateFlow(0L)
    val position: StateFlow<Long> = _position

    fun toggleTime() {
        positionJob?.run {
            positionJob?.cancel()
            positionJob = null
        } ?: run {
            positionJob = viewModelScope.launch {
                initPositionTimer()
                    .collect {
                        _position.value = it
                    }
            }
        }
    }

    fun initPositionTimer() = flow {
        while (true) {
            delay(1000)
            emit(exoPlayer.currentPosition)
        }
    }

    fun getEpisodeById(id: Long) {
        _state.value = State.Loading

        launch {
            repository.getEpisodeById(id)?.let {
                prepareEpisode(it)
                _position.value = 0
                playEpisode(it)
                toggleTime()
            } ?: run {
                _state.value = State.NotFound
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

    fun playEpisode(episode: Episode) {
        if (exoPlayer.isPlaying.not()) {
            exoPlayer.play()
            _state.value = State.WithEpisode(episode, true, exoPlayer.duration)
        }
    }

    fun pauseEpisode() {
        (state.value as? State.WithEpisode)?.let {
            if (it.playing) {
                exoPlayer.pause()
                _state.value = State.WithEpisode(it.episode, false, it.duration)
            }
        }
    }

    fun seekEpisodeTo(progress: Float) {
        exoPlayer.seekTo((exoPlayer.duration.toFloat() * progress).toLong())
    }

    sealed class State {
        data object Loading : State()
        data object NotFound : State()
        data class WithEpisode(
            val episode: Episode,
            val playing: Boolean,
            val duration: Long
        ) : State()
    }
}
