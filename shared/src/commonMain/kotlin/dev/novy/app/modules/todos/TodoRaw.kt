package dev.novy.app.modules.todos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoRaw(
    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,
)
