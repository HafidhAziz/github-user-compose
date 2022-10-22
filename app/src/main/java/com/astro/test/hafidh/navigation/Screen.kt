package com.astro.test.hafidh.navigation

sealed class Screen(val route: String){
    object Home: Screen("home_screen")
}