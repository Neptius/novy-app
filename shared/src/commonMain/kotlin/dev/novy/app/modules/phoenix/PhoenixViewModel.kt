package dev.novy.app.modules.phoenix

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.*
import dev.novy.app.modules.todos.TodosState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class PhoenixViewModel : ViewModel(), KoinComponent {
    private val phoenixUseCase: PhoenixUseCase by inject()

    private val _phoenixState: MutableStateFlow<PhoenixState> =
        MutableStateFlow(viewModelScope, PhoenixState())

    @NativeCoroutinesState
    val phoenixState: StateFlow<PhoenixState> = _phoenixState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        PhoenixState()
    )

    fun start() {
        viewModelScope.coroutineScope.launch {

        }
    }

    fun stop() {
        viewModelScope.coroutineScope.launch {

        }
    }

    fun join() {
        viewModelScope.coroutineScope.launch {

        }
    }
}