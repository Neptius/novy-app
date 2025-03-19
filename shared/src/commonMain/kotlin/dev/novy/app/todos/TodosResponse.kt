package dev.novy.app.todos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodosResponse(
    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val totalResults: Int,

    @SerialName("articles")
    val todos: List<TodoRaw>
)