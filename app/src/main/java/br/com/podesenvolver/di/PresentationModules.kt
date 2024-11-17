package br.com.podesenvolver.di

import br.com.podesenvolver.presentation.podcastDetail.PodcastDetailViewModel
import br.com.podesenvolver.presentation.rssFeed.RSSFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { RSSFeedViewModel(get()) }
    viewModel { PodcastDetailViewModel(get()) }
}
