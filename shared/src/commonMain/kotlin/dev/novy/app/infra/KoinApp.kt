package dev.novy.app.infra

import dev.novy.app.appModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config : KoinAppDeclaration? = null){
    startKoin {
        printLogger()
        includes(config)
        modules(appModule)
    }
}