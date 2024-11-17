package br.com.podesenvolver.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.xml.xml
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlConfig

class KtorClientConfig {
    companion object {
        fun config(): HttpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                xml(
                    XML {
                        autoPolymorphic = true
                        unknownChildHandler = XmlConfig.IGNORING_UNKNOWN_CHILD_HANDLER
                    }
                )
            }
        }
    }
}
