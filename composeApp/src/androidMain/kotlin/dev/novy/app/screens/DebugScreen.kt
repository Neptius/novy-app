package dev.novy.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.novy.app.Platform
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun DebugScreen() {
    MaterialTheme {
        Column {
            Toolbar()
            Content()
        }
    }
}

@Composable
private fun Toolbar() {
    TopAppBar(
        title = { Text("Debug Screen") }
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

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
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