package dev.novy.app.modules.phoenix.domain.repositories

interface PhoenixRepository {
    suspend fun connect() {}
    suspend fun disconnect() {}
    suspend fun joinChannel(channel: String) {}
}