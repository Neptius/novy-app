package dev.novy.app.phoenixchannel

import kotlinx.serialization.json.*

data class PhoenixMessage(
    val joinReference: String?,
    val messageReference: String,
    val topicName: String,
    val eventName: String,
    val payload: JsonObject
)
