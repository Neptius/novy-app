package dev.novy.app.todos

data class Todo (
    val title: String,
    val description: String,
    val completed: Boolean = false
)
