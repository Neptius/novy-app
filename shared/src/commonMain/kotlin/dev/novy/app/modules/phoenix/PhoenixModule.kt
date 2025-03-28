package dev.novy.app.modules.phoenix

import dev.novy.app.modules.phoenix.data.datasources.PhoenixChannel
import dev.novy.app.modules.phoenix.data.datasources.PhoenixDatasource
import dev.novy.app.modules.phoenix.data.datasources.PhoenixDatasourceImpl
import dev.novy.app.modules.phoenix.data.datasources.PhoenixSocket
import dev.novy.app.modules.phoenix.data.repositories.PhoenixRepositoryImpl
import dev.novy.app.modules.phoenix.domain.repositories.PhoenixRepository
import dev.novy.app.modules.phoenix.domain.usecases.PhoenixUseCase
import dev.novy.app.modules.phoenix.presentation.viewmodel.PhoenixViewModel
import org.koin.core.module.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

var phoenixModule = module {
    singleOf(::PhoenixDatasourceImpl) { bind<PhoenixDatasource>() }
    singleOf(::PhoenixRepositoryImpl) { bind<PhoenixRepository>() }
    singleOf(::PhoenixSocket)
    singleOf(::PhoenixUseCase)
    singleOf(::PhoenixChannel)
    singleOf(::PhoenixMessage)
    singleOf(::PhoenixViewModel)
}