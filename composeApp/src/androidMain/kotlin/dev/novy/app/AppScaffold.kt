package dev.novy.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.novy.app.screens.DebugScreen
import dev.novy.app.screens.TodosScreen
import dev.novy.app.screens.Screens
import dev.novy.app.todos.TodosViewModel

@Composable
fun AppScaffold(todosViewModel: TodosViewModel) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            todosViewModel = todosViewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    todosViewModel: TodosViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.TODOS.route,
        modifier = modifier,
    ) {
        composable(Screens.TODOS.route) {
            TodosScreen(
                onDebugButtonClick = {
                    navController.navigate(Screens.DEBUG.route)
                },
                todosViewModel = todosViewModel
            )
        }

        composable(Screens.DEBUG.route) {
            DebugScreen(
                onUpButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}