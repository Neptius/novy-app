package dev.novy.app

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

actual class Platform() {
    actual val osName: String = "Android"
    actual val osVersion: String = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String = Build.MODEL
    actual val density: Int = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logSystemInfo() {
        Log.d(
            "Platform",
            "OS: $osName $osVersion, Device: $deviceModel, Density: $density"
        )
    }
}