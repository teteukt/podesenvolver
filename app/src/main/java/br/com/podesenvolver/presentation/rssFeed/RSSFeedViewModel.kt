package br.com.podesenvolver.presentation.rssFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.domain.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RSSFeedViewModel(private val podcastRepository: PodcastRepository) : ViewModel() {

    private val _rssFeedUiState = MutableStateFlow<RSSFeedUIState>(RSSFeedUIState.Initial)
    val rssFeedUiState: StateFlow<RSSFeedUIState> = _rssFeedUiState

    fun fetchPodcast(url: String) {
        _rssFeedUiState.value = RSSFeedUIState.Loading

        viewModelScope.launch {
            podcastRepository.getPodcast(url)
                .catch { _rssFeedUiState.value = RSSFeedUIState.Error(it) }
                .collect { podcast: Podcast? ->
                    podcast?.let {
                        _rssFeedUiState.value = RSSFeedUIState.Success(it)
                    } ?: run {
                        _rssFeedUiState.value = RSSFeedUIState.NotFound
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