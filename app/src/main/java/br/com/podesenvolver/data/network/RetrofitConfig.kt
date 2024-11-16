package br.com.podesenvolver.data.network

import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitConfig {

    companion object {
        private val strategy = AnnotationStrategy()
        private val serializer = Persister(strategy)
        fun createPodcastApi(): PodcastApi = Retrofit.Builder()
            .baseUrl("https://anchor.fm/s/7a186bc/podcast/rss/")
            .addConverterFactory(
                SimpleXmlConverterFactory.createNonStrict(serializer)
            )
            .build()
            .create(PodcastApi::class.java)
    }
}