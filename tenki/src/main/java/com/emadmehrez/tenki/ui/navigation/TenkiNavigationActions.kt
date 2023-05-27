package com.emadmehrez.tenki.ui.navigation

import androidx.navigation.NavHostController

class TenkiNavigationActions(private val navController: NavHostController) {
    fun navigateTo(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

object TenkiRoute {
    const val DAILY_FORECAST = "daily_forecast"
    const val LOCATIONS = "locations"
    const val SETTINGS = "settings"
}