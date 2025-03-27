package dev.novy.app.modules.todos

data class Todo (
    val title: String,
    val description: String,
    val completed: Boolean = false
)
