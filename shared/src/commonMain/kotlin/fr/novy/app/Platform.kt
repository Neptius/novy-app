package fr.novy.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform