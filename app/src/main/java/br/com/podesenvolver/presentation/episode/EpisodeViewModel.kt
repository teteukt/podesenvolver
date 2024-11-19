package br.com.podesenvolver.presentation.episode

import androidx.annotation.OptIn
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.domain.Episode
import br.com.podesenvolver.domain.Podcast
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.extensions.orZero
import br.com.podesenvolver.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.math.max

class EpisodeViewModel(
    private val repository: LocalPodcastRepository,
    private val exoPlayer: ExoPlayer
) : BaseViewModel() {

    private lateinit var podcast: Podcast

    private var positionJob: Job? = null

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state

    private val _position = MutableStateFlow(PositionDuration())
    val position: StateFlow<PositionDuration> = _position

    init {
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == STATE_ENDED) {
                    seekToNextEpisode()
                }
            }
        })
    }

    private fun startTimer() {
        positionJob?.run {
            positionJob?.cancel()
            positionJob = null
        }

        positionJob = viewModelScope.launch {
            initPositionTimer()
                .collect {
                    _position.value = it
                }
        }
    }

    private fun initPositionTimer() = flow {
        while (true) {
            emit(PositionDuration(exoPlayer.currentPosition, max(0, exoPlayer.duration)))
            delay(1000)
        }
    }

    fun getEpisodeById(podcastId: Long, episodeId: Long) {
        _state.value = State.Loading

        launch {
            repository.getPodcastById(podcastId)
                ?.also { podcast = it }?.episodes?.find { it.id == episodeId }?.let {
                prepareEpisode(it)
                _position.value = PositionDuration()
                playEpisode(it)
                startTimer()
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

    fun seekToNextEpisode() {
        (state.value as? State.WithEpisode)?.episode?.let {
            podcast.episodes.getOrNull(it.index + 1)?.let { episode ->
                getEpisodeById(podcast.cacheId, episode.id)
            } ?: run {
                getEpisodeById(podcast.cacheId, podcast.episodes.first().id)
            }
        }
    }

    fun seekToPreviousEpisode() {
        (state.value as? State.WithEpisode)?.episode?.let {
            podcast.episodes.getOrNull(it.index - 1)?.let { episode ->
                getEpisodeById(podcast.cacheId, episode.id)
            } ?: run {
                getEpisodeById(podcast.cacheId, podcast.episodes.last().id)
            }
        }
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
