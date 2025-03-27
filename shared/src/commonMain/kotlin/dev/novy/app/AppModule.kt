package dev.novy.app

import dev.novy.app.modules.common.httpModule
import dev.novy.app.modules.phoenix.phoenixModule
import dev.novy.app.modules.todos.todosModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: List<Module> = listOf(
    todosModule,
    httpModule,
    phoenixModule,
    module {
        factory { getPlatform() }
    }
)