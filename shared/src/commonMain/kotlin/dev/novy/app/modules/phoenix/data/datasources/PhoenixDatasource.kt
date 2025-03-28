package dev.novy.app.modules.phoenix.data.datasources

interface PhoenixDatasource {
    suspend fun connect(): Boolean
    suspend fun disconnect()
    suspend fun joinChannel(channel: String)
}

class PhoenixDatasourceImpl(
    private val phoenixSocket: PhoenixSocket
): PhoenixDatasource {
    override suspend fun connect(): Boolean {
        return phoenixSocket.connect("ws://10.0.2.2:4000/socket/websocket?vsn=2.0.0")
    }

    override suspend fun disconnect() {
        phoenixSocket.disconnect()
    }

    override suspend fun joinChannel(channel: String) {
        phoenixSocket.channel(channel).join()
    }
}