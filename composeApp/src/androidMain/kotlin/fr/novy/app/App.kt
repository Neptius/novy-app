package fr.novy.app

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Divider
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App() {
    KoinApplication(application = koinAndroidConfiguration(LocalContext.current)) {
        MaterialTheme {
            val greeting = remember { Greeting().greet() }
            Column(
                modifier = Modifier.padding(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                greeting.forEach { greeting ->
                    Text(greeting)
                    Divider()
                }
            }
        }
    }
}

fun koinAndroidConfiguration(context: Context): KoinAppDeclaration = {
    androidContext(context)
    androidLogger()
    koinSharedConfiguration()
}