package dev.novy.app.modules.phoenix.presentation.viewmodel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import dev.novy.app.modules.phoenix.PhoenixState
import dev.novy.app.modules.phoenix.domain.usecases.PhoenixUseCase

open class PhoenixViewModel : ViewModel(), KoinComponent {
    private val phoenixUseCase: PhoenixUseCase by inject()

    private val _phoenixState =
        MutableStateFlow(viewModelScope, PhoenixState())

    @NativeCoroutinesState
    val phoenixState = _phoenixState.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(),
        PhoenixState()
    )

    fun start() {
        viewModelScope.coroutineScope.launch {
            println("PhoenixViewModel: start()")
            phoenixUseCase.connect()
        }
    }

    fun stop() {
        viewModelScope.coroutineScope.launch {
            println("PhoenixViewModel: stop()")
            phoenixUseCase.disconnect()
        }
    }

    fun join() {
        viewModelScope.coroutineScope.launch {
            println("PhoenixViewModel: join()")
            phoenixUseCase.joinChannel("debug")
        }
    }

    fun ping() {
        viewModelScope.coroutineScope.launch {
            println("PhoenixViewModel: ping()")
        }
    }

    fun info() {
        viewModelScope.coroutineScope.launch {
            println("PhoenixViewModel: info()")
            println("PhoenixViewModel: ${phoenixState.value}")
        }
    }
}