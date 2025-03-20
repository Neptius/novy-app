package dev.novy.app.todos.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import dev.novy.app.todos.TodosService
import dev.novy.app.todos.TodosUseCase
import dev.novy.app.todos.TodosViewModel
import org.koin.dsl.module

val todosModule = module {
    singleOf(::TodosService)
    singleOf(::TodosUseCase)
    singleOf(::TodosViewModel)
}