package br.com.podesenvolver.data.network

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitConfig {

    companion object {
        fun createPodcastApi(): PodcastApi = Retrofit.Builder()
            .build()
            .create(PodcastApi::class.java)
    }
}