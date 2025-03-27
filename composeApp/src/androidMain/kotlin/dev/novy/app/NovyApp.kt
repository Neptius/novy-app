package dev.novy.app

import android.app.Application
import android.util.Log
import dev.novy.app.modules.phoenix.PhoenixSocket
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NovyApp : Application() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch(Dispatchers.IO) {
            initApp()
        }
    }

    private fun initApp() {
        // Initialize the app
        Log.d("NovyApp", "Initializing NovyApp")

        // Phoenix Channels
        // val socket = PhoenixSocket("ws://10.0.2.2:4000/socket/websocket?vsn=2.0.0")
        // socket.connect()
    }
}