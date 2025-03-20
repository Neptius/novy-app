package dev.novy.app

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

class AndroidPlatform : Platform {
    override val osName: String = "Android"
    override val osVersion: String = "${Build.VERSION.SDK_INT}"
    override val deviceModel: String = Build.MODEL
    override val density: Int = round(Resources.getSystem().displayMetrics.density).toInt()

    override fun logSystemInfo() {
        Log.d(
            "Platform",
            "OS: $osName $osVersion, Device: $deviceModel, Density: $density"
        )
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()