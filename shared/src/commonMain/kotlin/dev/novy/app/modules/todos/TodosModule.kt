package dev.novy.app.modules.todos

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val todosModule = module {
    singleOf(::TodosService)
    singleOf(::TodosUseCase)
    singleOf(::TodosViewModel)
}