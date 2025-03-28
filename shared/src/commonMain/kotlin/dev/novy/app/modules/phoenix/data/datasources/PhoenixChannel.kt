package dev.novy.app.modules.phoenix.data.datasources

import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.websocket.Frame

class PhoenixChannel(
    internal val topic: String,
    private val session: DefaultClientWebSocketSession
) {

    private val ref = 0

    suspend fun join() {
        println("Joining channel $topic")
    }

    fun leave() {
        println("Leaving channel $topic")
    }

    fun push(event: String, payload: Map<String, Any>) {
        println("Pushing event $event to channel $topic with payload $payload")
    }

    fun on(event: String, callback: (Map<String, Any>) -> Unit) {
        println("Listening for event $event on channel $topic")
    }
}