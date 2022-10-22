package com.astro.test.hafidh.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.astro.test.hafidh.presentation.home.HomeScreen

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun NavGraphSetup(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}