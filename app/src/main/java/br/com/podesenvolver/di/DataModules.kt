package br.com.podesenvolver.di

import br.com.podesenvolver.data.local.LocalPodcastDataSource
import br.com.podesenvolver.data.local.repository.LocalPodcastRepository
import br.com.podesenvolver.data.local.repository.LocalPodcastRepositoryImpl
import br.com.podesenvolver.data.network.KtorClientConfig
import br.com.podesenvolver.data.network.PodcastDataSource
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepositoryImpl
import org.koin.dsl.module

val dataModules = module {
    single { KtorClientConfig.config() }
    single { PodcastDataSource(get()) }
    single { LocalPodcastDataSource() }
    factory<PodcastRepository> { PodcastRepositoryImpl(get()) }
    factory<LocalPodcastRepository> { LocalPodcastRepositoryImpl(get()) }
}
