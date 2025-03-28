package dev.novy.app.modules.phoenix.domain.usecases

import dev.novy.app.modules.phoenix.domain.repositories.PhoenixRepository
import dev.novy.app.modules.todos.TodosService
import org.koin.core.component.KoinComponent

class PhoenixUseCase(
    private val repository: PhoenixRepository
): KoinComponent {
    suspend fun connect() = repository.connect()
    suspend fun disconnect() = repository.disconnect()
    suspend fun joinChannel(channel: String) = repository.joinChannel(channel)
}