package dev.novy.app.todos

data class TodosState (
    val todos: List<Todo> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
