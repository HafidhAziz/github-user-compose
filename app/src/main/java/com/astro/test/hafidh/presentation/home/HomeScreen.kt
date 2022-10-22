package com.astro.test.hafidh.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.astro.test.hafidh.presentation.list.ListContent

@ExperimentalComposeUiApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val searchQuery by homeViewModel.query
    val users = homeViewModel.users.collectAsLazyPagingItems()
    val closeButtonVisibility = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                text = searchQuery,
                onTextChange = {
                    closeButtonVisibility.value = it.isNotEmpty()
                    homeViewModel.setQuery(query = it)
                },
                onSearchClicked = {
                    homeViewModel.searchUsers(query = it)
                },
                onCloseClicked = {
                    homeViewModel.setQuery(query = "")
                    closeButtonVisibility.value = false
                },
                closeButtonVisibility = closeButtonVisibility.value
            )
        },
        content = {
            ListContent(items = users)
        }
    )
}