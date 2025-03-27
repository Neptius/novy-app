package dev.novy.app.modules.phoenix

import dev.novy.app.modules.todos.Todo
import dev.novy.app.modules.todos.TodoRaw
import dev.novy.app.modules.todos.TodosService
import org.koin.core.component.KoinComponent

class PhoenixUseCase(
    private val todosService: TodosService
): KoinComponent {

}