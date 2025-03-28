package dev.novy.app.modules.phoenix

import io.ktor.websocket.Frame
import kotlinx.serialization.json.*

typealias Payload = Map<String, Any?>

data class PhoenixMessage(
    val joinReference: String?,
    val messageReference: String,
    val topicName: String,
    val eventName: String,
    val payload: Payload
)
