package br.edu.ifpe.alvarium.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AlvariumApp() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            AlvariumBottomBar(
                currentRoute = currentRoute,
                onNavigate = { navController.navigate(it) }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavHost(navController)
        }
    }
}
