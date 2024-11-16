package br.com.podesenvolver.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import tw.ktrssreader.kotlin.model.channel.ITunesChannelData
import tw.ktrssreader.kotlin.parser.ITunesParser

class PodcastDataSource(private val ktorClientConfig: HttpClient) {
    suspend fun getPodcast(url: String): ITunesChannelData {
        val response = ktorClientConfig.get(url).bodyAsText()
        return ITunesParser().parse(response)
    }
}