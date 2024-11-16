package br.com.podesenvolver.presentation.rssFeed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RSSFeedViewModel(private val podcastRepository: PodcastRepository) : ViewModel() {

    val rssUrlText = mutableStateOf("")

    private val _rssFeedUiState = MutableStateFlow<RSSFeedUIState>(RSSFeedUIState.Initial)
    val rssFeedUiState: StateFlow<RSSFeedUIState> = _rssFeedUiState

    fun shouldEnableSearchButton() = rssFeedUiState.value != RSSFeedUIState.Loading

    fun fetchPodcast() {
        _rssFeedUiState.value = RSSFeedUIState.Loading

        viewModelScope.launch {
            podcastRepository.getPodcast(rssUrlText.value)
                .catch {
                    _rssFeedUiState.value = RSSFeedUIState.Error(it)
                }
                .collect { podcast ->
                    podcast.let {
                        _rssFeedUiState.value = RSSFeedUIState.Success(it)
                    }
                }
        }
    }

    sealed class RSSFeedUIState {
        data object Initial : RSSFeedUIState()
        data class Success(val podcast: Podcast) : RSSFeedUIState()
        data object NotFound : RSSFeedUIState()
        data class Error(val exception: Throwable) : RSSFeedUIState()
        data object Loading : RSSFeedUIState()
    }
}