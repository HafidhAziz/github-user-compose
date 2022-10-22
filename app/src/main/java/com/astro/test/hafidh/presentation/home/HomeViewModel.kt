package com.astro.test.hafidh.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.astro.test.hafidh.data.model.UserResponseData
import com.astro.test.hafidh.data.repository.APIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: APIRepository) : ViewModel() {

    private val _query = mutableStateOf("")
    val query = _query

    private val _users = MutableStateFlow<PagingData<UserResponseData>>(PagingData.empty())
    val users = _users

    fun setQuery(query: String) {
        _query.value = query
    }

    fun searchUsers(query: String) {
        viewModelScope.launch {
            apiRepository.searchUsers(query = query).cachedIn(viewModelScope).collect {
                _users.value = it
            }
        }
    }

}