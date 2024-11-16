package br.com.podesenvolver.di

import br.com.podesenvolver.data.network.PodcastDataSource
import br.com.podesenvolver.data.network.RetrofitConfig
import br.com.podesenvolver.data.network.repository.PodcastRepository
import br.com.podesenvolver.data.network.repository.PodcastRepositoryImpl
import org.koin.dsl.module

val dataModules = module {
    single { RetrofitConfig.createPodcastApi() }
    single { PodcastDataSource(get()) }
    factory<PodcastRepository> { PodcastRepositoryImpl(get()) }
}