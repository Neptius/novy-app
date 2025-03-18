package fr.novy.app

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TodoRepositoryImpl) { bind<TodoRepository>() }
    factoryOf(::TodoPresenter)
}