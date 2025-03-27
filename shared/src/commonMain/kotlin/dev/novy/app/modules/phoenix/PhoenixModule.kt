package dev.novy.app.modules.phoenix

import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

var phoenixModule = module {
    singleOf(::PhoenixUseCase)
    singleOf(::PhoenixViewModel)
    singleOf(::PhoenixChannel)
    singleOf(::PhoenixMessage)
    singleOf(::PhoenixSocket)
}