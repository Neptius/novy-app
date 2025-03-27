package dev.novy.app.modules.phoenix

import kotlinx.serialization.json.*

data class PhoenixMessage(
    val joinReference: String?,
    val messageReference: String,
    val topicName: String,
    val eventName: String,
    val payload: JsonObject
)
