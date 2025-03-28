package dev.novy.app.modules.phoenix.data.datasources

import PhoenixMessage
import dev.novy.app.modules.phoenix.PhoenixMessage
import dev.novy.app.modules.phoenix.data.datasources.PhoenixChannel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import kotlinx.coroutines.Job
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class PhoenixSocket(
    private val httpClient: HttpClient
) {
    private val channels = mutableListOf<PhoenixChannel>()
    private var session: DefaultClientWebSocketSession? = null
    private var heartbeatJob: Job? = null
    private var ref: Int = 0
    private val heartbeatInterval: Long = 30_000L
    private var connected: Boolean = false

    suspend fun connect(url: String): Boolean {
        if (connected) {
            println("Already connected!")
            return connected
        }

        return try {
            println("Connecting to $url")
            session = httpClient.webSocketSession(url)
            connected = true
            println("Connected!")

            // Démarrer le heartbeat
            startHeartbeat()

            true
        } catch (e: Exception) {
            println("Error connecting to socket: ${e.message}")
            connected = false
            false
        }
    }

    suspend fun disconnect() {
        if (!connected) {
            println("Already disconnected!")
            return
        }

        try {
            session?.close()
        } catch (e: Exception) {
            println("Error while disconnecting: ${e.message}")
        } finally {
            connected = false
            heartbeatJob?.cancel()
            session = null
            println("Disconnected!")
        }
    }


    fun channel(topic: String): PhoenixChannel {
        if (!connected) {
            throw IllegalStateException("Socket is not connected")
        }

        return channels.find { it.topic == topic } ?: PhoenixChannel(topic, session!!).also {
            channels.add(it)
        }
    }

    private fun startHeartbeat() {
        heartbeatJob?.cancel()
        heartbeatJob = CoroutineScope(coroutineContext).launch {
            while (connected) {
                delay(heartbeatInterval)
                heartbeat()
            }
        }
    }

    private suspend fun heartbeat() {
        println("Sending heartbeat")

    }

    fun sendMessage(msg: PhoenixMessage) {
        if (!connected) {
            throw IllegalStateException("Socket is not connected")
        }

        val message = msg.copy(
            messageReference = makeRef()
        )

        val frame = Frame.Text(Json.encodeToString(PhoenixMessage.serializer(), message))
        session?.send(frame)
    }

    private fun makeRef(): String {
        ref = if (ref == Int.MAX_VALUE) 0 else ref + 1
        return ref.toString()
    }
}