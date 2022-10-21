package com.astro.test.hafidh.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchUsersResultResponse(
    @SerialName("total_count")
    val total_count: Int? = 0,
    @SerialName("incomplete_results")
    val incomplete_results: Boolean? = false,
    @SerialName("items")
    val items: List<UserResponseData>
)

