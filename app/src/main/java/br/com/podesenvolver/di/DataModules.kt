package br.com.podesenvolver.di

import br.com.podesenvolver.data.local.DatabaseConfig
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.data.local.repository.LocalPodcastRepositoryImpl
import br.com.podesenvolver.data.network.KtorClientConfig
import br.com.podesenvolver.data.network.PodcastDataSource
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepositoryImpl
import br.com.podesenvolver.media.MediaProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModules = module {
    single { KtorClientConfig.config() }
    single { DatabaseConfig.create(androidApplication()) }
    single { PodcastDataSource(get()) }
    single { MediaProvider.createExoPlayer(androidApplication()) }
    factory<PodcastRepository> { PodcastRepositoryImpl(get()) }
    factory<LocalPodcastRepository> { LocalPodcastRepositoryImpl(get()) }
}
