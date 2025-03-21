package dev.novy.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.novy.app.screens.DebugScreen
import dev.novy.app.screens.TodosScreen
import dev.novy.app.screens.Screens
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    KoinContext {
        Scaffold {
            AppNavHost(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
                }
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