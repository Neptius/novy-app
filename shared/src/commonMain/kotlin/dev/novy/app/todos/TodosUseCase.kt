package dev.novy.app.todos

class TodosUseCase(
    private val todosService: TodosService
) {
    suspend fun getTodos(): List<Todo> {
        val todosRaw = todosService.fetchTodos()

        return mapTodos(todosRaw)
    }

    private fun mapTodos(todosRaw: List<TodoRaw>): List<Todo> = todosRaw.map {
        Todo(
            title = it.title,
            description = it.description ?: "Cliquer pour en savoir plus",
            completed = false
        )
    }
}