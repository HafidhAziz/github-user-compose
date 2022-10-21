package com.astro.test.hafidh.data.service

import com.astro.test.hafidh.data.model.SearchUsersResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int
    ): SearchUsersResultResponse
}