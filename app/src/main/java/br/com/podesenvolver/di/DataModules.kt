package br.com.podesenvolver.di

import br.com.podesenvolver.data.network.RetrofitConfig
import org.koin.dsl.module

val dataModules = module {
    single { RetrofitConfig.createPodcastApi() }
}