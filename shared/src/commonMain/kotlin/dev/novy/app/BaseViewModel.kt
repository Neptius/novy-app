package dev.novy.app

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val scope : CoroutineScope
}