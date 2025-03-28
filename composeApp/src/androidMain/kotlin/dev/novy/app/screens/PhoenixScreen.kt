package dev.novy.app.screens

import ConnectionBanner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import dev.novy.app.modules.phoenix.presentation.viewmodel.PhoenixViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun PhoenixScreen(
    onDebugButtonClick: () -> Unit,
    onPhoenixButtonClick: () -> Unit,
) {
    MaterialTheme {
        Column {
            Toolbar(onDebugButtonClick, onPhoenixButtonClick)
            Content()
        }
    }
}

@Composable
private fun Toolbar(
    onDebugButtonClick: () -> Unit,
    onPhoenixButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text("Phoenix Channels") },
        actions = {
            IconButton(onClick = onDebugButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Debug Info Button",
                )
            }
            IconButton(onClick = onPhoenixButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Phoenix Channels Button",
                )
            }
        }
    )
    ConnectionBanner()
}

@Composable
private fun Content(
    phoenixViewModel: PhoenixViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                phoenixViewModel.start()
            }
        ) {
            Text("START")
        }

        Button(
            onClick = {
                phoenixViewModel.join()
            },
        ) {
            Text("JOIN")
        }

        Button(
            onClick = {
                phoenixViewModel.ping()
            },
        ) {
            Text("SEND PING")
        }

        Button(
            onClick = {
                phoenixViewModel.stop()
            },
        ) {
            Text("STOP")
        }

        Button(
            onClick = {
                phoenixViewModel.info()
            },
        ) {
            Text("INFO")
        }
    }
}

