package dev.novy.app.modules.phoenix

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession

class PhoenixChannel(
    private val topic: String,
    private val session: DefaultClientWebSocketSession
) {

    private val ref = 0

    fun join() {
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

    @NativeCoroutines
    suspend fun sendMessage() {
        // session.send()
    }
}