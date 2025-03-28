package dev.novy.app.modules.phoenix.data.datasources

import dev.novy.app.modules.phoenix.data.datasources.PhoenixChannel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.close

class PhoenixSocket(
    private val httpClient: HttpClient
) {
    private val channels = listOf<PhoenixChannel>()
    private lateinit var session: DefaultClientWebSocketSession
    private var ref: Int = 0
    private val heartbeatInterval: Long = 30_000L
    private var connected: Boolean = false

    suspend fun connect(url: String) {
        if (connected) {
            println("Already connected!")
            return
        }

        println("Connecting to $url")
        session = httpClient.webSocketSession(url)
        connected = true
        println("Connected!")
    }

    suspend fun disconnect() {
        if (!connected) {
            println("Already disconnected!")
            return
        }

        session.close()
        connected = false
        println("Disconnected!")
    }

    fun channel(topic: String): PhoenixChannel {
        if (!connected) {
            throw IllegalStateException("Socket is not connected")
        }

        if (channels.any { it.topic == topic }) {
            return channels.first { it.topic == topic }
        }

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