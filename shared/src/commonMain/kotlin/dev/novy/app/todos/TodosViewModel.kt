package dev.novy.app.todos

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class TodosViewModel : ViewModel(), KoinComponent {
    private val _todosState = MutableStateFlow(viewModelScope, TodosState(loading = true))

    private val todosUseCase: TodosUseCase by inject()

    @NativeCoroutinesState
    val todosState = _todosState.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            val fetchedTodos = todosUseCase.getTodos()

            _todosState.update { it.copy(todos = fetchedTodos, loading = false) }
        }
    }
}