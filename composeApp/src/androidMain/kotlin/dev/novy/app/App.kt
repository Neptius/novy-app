package dev.novy.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

import androidx.navigation.compose.*
import dev.novy.app.screens.DebugScreen
import dev.novy.app.screens.PhoenixScreen
import dev.novy.app.screens.TodosScreen
import dev.novy.app.screens.Screens
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val topLevelRoutes = listOf(
        TopLevelRoute("Phoenix", Screens.PHOENIX.route, Icons.AutoMirrored.Sharp.KeyboardArrowLeft),
        TopLevelRoute("DEBUG", Screens.DEBUG.route, Icons.AutoMirrored.Sharp.KeyboardArrowRight)
    )
    KoinContext {
        Scaffold(
            backgroundColor = Color(0xFF020617),
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                Box (
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Transparent)

                ) {
                    FloatingActionButton(
                        backgroundColor = Color(0x335eead4),
                        modifier = Modifier
                            .border(
                                1.dp,
                                Color(0xFF03DAC6),
                                CutCornerShape(10.dp, 0.dp, 10.dp, 0.dp)
                            ),
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        shape = CutCornerShape(10.dp, 0.dp, 10.dp, 0.dp),
                        onClick = { /* TODO: Add action */ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(45.dp),
                            tint = Color(0xFF5eead4)
                        )
                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier.padding(10.dp),
                ) {
                    BottomNavigation(
                        elevation = 0.dp,
                        modifier = Modifier
                            .clip(CutCornerShape(10.dp, 0.dp, 10.dp, 0.dp))
                            .border(1.dp, Color(0xFF03DAC6), CutCornerShape(10.dp, 0.dp, 10.dp, 0.dp)),
                        backgroundColor = Color(0x335eead4)
                    ) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        topLevelRoutes.forEach { topLevelRoute ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        topLevelRoute.icon,
                                        contentDescription = topLevelRoute.name,
                                        tint = Color(0xFF5eead4)
                                    )
                                },
                                label = {
                                    Text(
                                        topLevelRoute.name,
                                        color = Color(0xFF5eead4)
                                    )
                                        },
                                selected = currentDestination?.hierarchy?.any {
                                    it.hasRoute(
                                        topLevelRoute.route::class
                                    )
                                } == true,
                                onClick = {
                                    navController.navigate(topLevelRoute.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) {
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