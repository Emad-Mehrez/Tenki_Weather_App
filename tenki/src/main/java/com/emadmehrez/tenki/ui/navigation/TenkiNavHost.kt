package com.emadmehrez.tenki.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import com.emadmehrez.tenki.ui.screen.dailyforecast.DailyForecastUiState
import com.emadmehrez.tenki.ui.util.TenkiContentType

@Composable
fun TenkiNavHost(
    modifier: Modifier = Modifier,
    contentType: TenkiContentType,
    displayFeatures: List<DisplayFeature>,
    dailyForecastUiState: DailyForecastUiState,
    closeDetailsScreen: () -> Unit,
    navigateToDetailsScreen: (Long, TenkiContentType) -> Unit,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: TenkiRoute.DAILY_FORECAST
    val navigationActions = remember(navController) {
        TenkiNavigationActions(navController)
    }
    NavHost(
        navController = navController,
        startDestination = TenkiRoute.DAILY_FORECAST,
        modifier = modifier
    ) {
        composable(route = TenkiRoute.DAILY_FORECAST) {

        }
        composable(route = TenkiRoute.LOCATIONS) {

        }
        composable(route = TenkiRoute.SETTINGS) {

        }
    }
}