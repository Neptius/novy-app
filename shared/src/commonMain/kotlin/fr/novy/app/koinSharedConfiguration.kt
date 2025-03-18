package fr.novy.app

import org.koin.dsl.KoinAppDeclaration

fun koinSharedConfiguration() : KoinAppDeclaration = {
    modules(appModule)
}