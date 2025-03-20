package dev.novy.app.di

import dev.novy.app.getPlatform
import dev.novy.app.todos.di.todosModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule: List<Module> = listOf(
    todosModule,
    httpModule,
    module {
        factory { getPlatform() }
    }
)