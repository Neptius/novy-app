package dev.novy.app.modules.phoenix

object PhoenixConstants {
    const val TIMEOUT = 10000
    enum class SOCKET_STATES(val state: String) {
        CONNECTING("connecting"),
        OPEN("open"),
        CLOSING("closing"),
        CLOSED("closed"),
    }
    enum class CHANNEL_EVENTS(val evt: String) {
        CLOSE("phx_close"),
        ERROR("phx_error"),
        JOIN("phx_join"),
        REPLY("phx_reply"),
        LEAVE("phx_leave"),
    }
}