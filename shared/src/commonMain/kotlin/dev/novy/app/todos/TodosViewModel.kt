package dev.novy.app.todos

import dev.novy.app.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class TodosViewModel : BaseViewModel() {
    private val _todosState: MutableStateFlow<TodosState> =
        MutableStateFlow(TodosState(loading = true))

    val todosState: StateFlow<TodosState> get() = _todosState

    private val todosUseCase: TodosUseCase

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val todosService = TodosService(httpClient)
        todosUseCase = TodosUseCase(todosService)

        getTodos()
    }

    private fun getTodos() {
        scope.launch {
            val fetchedTodos = todosUseCase.getTodos()

            _todosState.emit(TodosState(todos = fetchedTodos))
        }
    }
}