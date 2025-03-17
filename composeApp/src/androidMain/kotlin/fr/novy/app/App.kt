package fr.novy.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Divider
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.novy.app.viewModels.CounterViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: CounterViewModel) {
    MaterialTheme {
        val greeting = remember { Greeting().greet() }
        val count by viewModel.counter.collectAsState()

        Column(
            modifier = Modifier.padding(all = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            greeting.forEach { greeting ->
                Text(greeting)
                Divider()
            }
        }


        Column {
            Text("Count: $count")
            Button(onClick = { viewModel.increment() }) {
                Text("Increment")
            }
        }
    }
}