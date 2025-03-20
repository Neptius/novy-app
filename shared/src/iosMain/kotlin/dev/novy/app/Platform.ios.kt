package dev.novy.app

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

class IOSPlatform: Platform {
    override val osName: String = UIDevice.currentDevice.systemName
    override val osVersion: String = UIDevice.currentDevice.systemVersion
    override val deviceModel: String = UIDevice.currentDevice.model
    override val density: Int = UIScreen.mainScreen.scale.toInt()

    override fun logSystemInfo() {
        NSLog(
            "Platform",
            "OS: $osName $osVersion, Device: $deviceModel, Density: $density"
        )
    }
}

actual fun getPlatform(): Platform = IOSPlatform()