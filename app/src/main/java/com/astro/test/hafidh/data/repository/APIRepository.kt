package com.astro.test.hafidh.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.astro.test.hafidh.data.model.UserResponseData
import com.astro.test.hafidh.data.paging.SearchPagingSource
import com.astro.test.hafidh.data.service.APIService
import com.astro.test.hafidh.domain.model.User
import com.astro.test.hafidh.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class APIRepository @Inject constructor(private val apiService: APIService) {

    fun searchImages(query: String): Flow<PagingData<UserResponseData>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                SearchPagingSource(apiService = apiService, query = query)
            }
        ).flow
    }
}