package dev.novy.app.modules.phoenix.domain.repositories

interface PhoenixRepository {
    suspend fun connect(): Boolean
    suspend fun disconnect()
    suspend fun joinChannel(channel: String)
}