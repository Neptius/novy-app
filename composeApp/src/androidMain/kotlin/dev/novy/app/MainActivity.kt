package dev.novy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.novy.app.screens.DebugScreen
import dev.novy.app.screens.TodosScreen
import dev.novy.app.todos.TodosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todosViewModel: TodosViewModel by viewModels()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    AppScaffold(todosViewModel)
                }
            }
        }
    }
}