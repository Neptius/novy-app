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
import dev.novy.app.screens.PhoenixScreen
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
        startDestination = Screens.PHOENIX.route,
        modifier = modifier,
    ) {
        composable(Screens.PHOENIX.route) {
            PhoenixScreen(
                onDebugButtonClick = {
                    navController.navigate(Screens.DEBUG.route)
                },
                onPhoenixButtonClick = {
                    navController.navigate(Screens.PHOENIX.route)
                }
            )
        }

        composable(Screens.TODOS.route) {
            TodosScreen(
                onUpButtonClick = {
                    navController.popBackStack()
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