package dev.novy.app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.novy.app.modules.todos.Todo
import dev.novy.app.modules.todos.TodosViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun TodosScreen(
    onUpButtonClick: () -> Unit,
    todosViewModel: TodosViewModel = koinViewModel()
) {
    val todosState = todosViewModel.todosState.collectAsState()

    LaunchedEffect(Unit) {
        todosViewModel.getTodos()
    }

    Column {
        AppBar(onUpButtonClick)
        if (todosState.value.loading)
            Loader()
        else if (todosState.value.error != null)
            ErrorMessage(message = todosState.value.error!!)
        else if (todosState.value.todos.isNotEmpty())
            TodosListView(todos = todosState.value.todos)
    }
}

@Composable
private fun AppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text("Todos") },
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
private fun TodosListView(
    todos: List<Todo>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(todos) { todo ->
            TodoView(todo = todo)
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
private fun TodoView(
    todo: Todo,
    todosViewModel: TodosViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier.clickable {
            todosViewModel.removeTodo(title = todo.title)
        }
    ) {
        Text(text = todo.title)
        Text(text = todo.description)
    }
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colors.surface,
            backgroundColor = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun ErrorMessage(
    message: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}