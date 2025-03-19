package dev.novy.app.todos

import dev.novy.app.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodosViewModel : BaseViewModel() {
    private val _todosState: MutableStateFlow<TodosState> =
        MutableStateFlow(TodosState(loading = true))

    val todosState: StateFlow<TodosState> get() = _todosState

    init {
        getTodos()
    }

    private fun getTodos() {
        scope.launch {

            delay(1500)

            _todosState.emit(TodosState(error = "Something went wrong"))

            delay(1500)

            val fetchedTodos = fetchTodos()

            _todosState.emit(TodosState(todos = fetchedTodos))
        }
    }

    private suspend fun fetchTodos(): List<Todo> = mockTodos

    private val mockTodos = listOf(
        Todo(
            title = "Buy groceries",
            description = "Milk, eggs, bread, and bananas",
            completed = false
        ),
        Todo(
            title = "Walk the dog",
            description = "Take Fido for a walk around the block",
            completed = true
        ),
        Todo(
            title = "Do laundry",
            description = "Wash, dry, and fold clothes",
            completed = false
        ),
        Todo(
            title = "Clean the house",
            description = "Vacuum, dust, and mop",
            completed = false
        ),
        Todo(
            title = "Work on app",
            description = "Add new features and fix bugs",
            completed = false
        ),
        Todo(
            title = "Read a book",
            description = "Find a good book and read for an hour",
            completed = true
        )
    )
}