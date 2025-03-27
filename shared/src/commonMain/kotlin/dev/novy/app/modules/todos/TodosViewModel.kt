package dev.novy.app.modules.todos

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class TodosViewModel : ViewModel(), KoinComponent {
    private val todosUseCase: TodosUseCase by inject()

    private val _todosState: MutableStateFlow<TodosState> =
        MutableStateFlow(viewModelScope, TodosState(loading = true))

    @NativeCoroutinesState
    val todosState: StateFlow<TodosState> = _todosState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        TodosState(loading = true)
    )

    fun getTodos() {
        viewModelScope.coroutineScope.launch {
            val fetchedTodos = todosUseCase.getTodos()

            _todosState.update { it.copy(todos = fetchedTodos, loading = false) }
        }
    }

    fun removeTodo(title: String) {
        viewModelScope.coroutineScope.launch {
            _todosState.update { it.copy(todos = it.todos.filter { it.title != title }) }
        }
    }
}