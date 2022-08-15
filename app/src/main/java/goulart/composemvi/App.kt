package goulart.composemvi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import goulart.composemvi.navigation.Route
import goulart.composemvi.presentation.features.main.MainScreen
import goulart.composemvi.ui.theme.ComposeMVITheme

@Composable
fun MviApp() {
    val navController = rememberNavController()

    ComposeMVITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(navController = navController, startDestination = Route.MainScreen) {
                mainScreenRoute(navController = navController)
            }
        }
    }
}

private fun NavGraphBuilder.mainScreenRoute(navController: NavController) {
    composable(Route.MainScreen) {
        MainScreen()
    }
}