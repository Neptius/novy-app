package dev.novy.app.phoenixchannel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.close

class PhoenixSocket(private val url: String) {
    private val channels = listOf<PhoenixChannel>()
    private lateinit var session: DefaultClientWebSocketSession
    private var ref: Int = 0

    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    @NativeCoroutines
    suspend fun connect() {
        client.webSocket(url) {
            session = this
            println("Connecting to $url")
        }
    }

    @NativeCoroutines
    suspend fun disconnect() {
        session.close()
        client.close()
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