package dev.novy.app.modules.todos

data class TodosState (
    val todos: List<Todo> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
