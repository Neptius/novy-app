package dev.novy.app

import android.app.Application
import android.util.Log
import dev.novy.app.modules.phoenix.data.datasources.PhoenixSocket
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
    }
}