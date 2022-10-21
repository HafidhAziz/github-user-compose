package com.astro.test.hafidh.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.astro.test.hafidh.data.model.UserResponseData
import com.astro.test.hafidh.data.service.APIService
import com.astro.test.hafidh.util.Constants.PAGE_SIZE

class SearchPagingSource(
    private val apiService: APIService,
    private val query: String
) : PagingSource<Int, UserResponseData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponseData> {
        val currentPage = params.key ?: 1
        return try {
            val response = apiService.searchUsers(query = query, perPage = PAGE_SIZE)
            if (response.items.isNotEmpty()) {
                LoadResult.Page(
                    data = response.items,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponseData>): Int? {
        return state.anchorPosition
    }

}