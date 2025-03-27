package dev.novy.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.novy.app.Platform
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun PhoenixScreen(
    onUpButtonClick: () -> Unit,
) {
    MaterialTheme {
        Column {
            Toolbar(onUpButtonClick)
            Content()
        }
    }
}

@Composable
private fun Toolbar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text("Phoenix Channels") },
        actions = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Up Button",
                )
            }
        }
    )
}

@Composable
private fun Content() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items) { row ->
            RowView(title = row.first, subTitle = row.second)
        }
    }

}

@Composable
private fun makeItems(): List<Pair<String, String>> {
    val platform = koinInject<Platform>()
    platform.logSystemInfo()

    return listOf(
        Pair("OS", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", "${platform.density}")
    )
}

@Composable
private fun RowView(title: String, subTitle: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.body2,
        )
    }
    Divider()
}