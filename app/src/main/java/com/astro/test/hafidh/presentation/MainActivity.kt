package com.astro.test.hafidh.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.astro.test.hafidh.navigation.NavGraphSetup
import com.astro.test.hafidh.ui.theme.GithubUserComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUserComposeTheme {
                val navController = rememberNavController()
                NavGraphSetup(navController = navController)
            }
        }
    }
}