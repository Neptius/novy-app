package dev.novy.app.modules.phoenix.data.repositories

import dev.novy.app.modules.phoenix.data.datasources.PhoenixDatasource
import dev.novy.app.modules.phoenix.domain.repositories.PhoenixRepository

class PhoenixRepositoryImpl(
    private val datasource: PhoenixDatasource
): PhoenixRepository {
    override suspend fun connect() = datasource.connect()
    override suspend fun disconnect() = datasource.disconnect()
    override suspend fun joinChannel(channel: String) = datasource.joinChannel(channel)
}