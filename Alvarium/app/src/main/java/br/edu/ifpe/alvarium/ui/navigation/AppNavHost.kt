package br.edu.ifpe.alvarium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.edu.ifpe.alvarium.ui.screens.converter.ConverterScreen
import br.edu.ifpe.alvarium.ui.screens.details.DetailsScreen
import br.edu.ifpe.alvarium.ui.screens.favorites.FavoritesScreen
import br.edu.ifpe.alvarium.ui.screens.main.MainScreen
import br.edu.ifpe.alvarium.ui.screens.splash.SplashScreen


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(onNavigateToHome = {
                navController.navigate(BottomNavItem.Home.route) {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable(BottomNavItem.Home.route) {
            MainScreen(
                onNavigateToDetails = { acronym ->
                    navController.navigate(AppRoute.DetailsScreen.build(acronym))
                }
            )
        }

        composable(BottomNavItem.Convert.route) {
            ConverterScreen()
        }

        composable(BottomNavItem.Favorites.route) {
            FavoritesScreen(
                onNavigateToDetails = { acronym ->
                    navController.navigate(AppRoute.DetailsScreen.build(acronym))
                }
            )
        }

        composable(
            route = "details/{coinId}",
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
            DetailsScreen(
                coinId = coinId,
                context = LocalContext.current,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
