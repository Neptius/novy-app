package dev.novy.app.modules.phoenix

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*

class PhoenixSocket(
    private val url: String,
    private val httpClient: HttpClient
) {
    private val channels = listOf<PhoenixChannel>()
    private lateinit var session: DefaultClientWebSocketSession
    private var ref: Int = 0

    suspend fun connect() {
        println("Connecting to $url")
        session = httpClient.webSocketSession(url)
    }

    suspend fun disconnect() {
        session.close()
        println("Disconnecting from $url")
    }

    fun channel(topic: String): PhoenixChannel {
        val channel = PhoenixChannel(topic, session)
        channels.plus(channel)
        return channel
    }

    internal fun makeRef(): String {
        this.ref = if (ref == Int.MAX_VALUE) 0 else ref + 1
        return ref.toString()
    }

    fun heartbeat() {
        println("Heartbeat")
    }
}