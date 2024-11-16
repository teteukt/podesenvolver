package br.com.podesenvolver.data.network

import br.com.podesenvolver.data.network.entity.PodcastRssResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PodcastApi {

    @GET
    suspend fun getPodcastRss(@Url url: String): PodcastRssResponse
}