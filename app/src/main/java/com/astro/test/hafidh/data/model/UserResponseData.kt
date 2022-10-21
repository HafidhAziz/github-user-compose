package com.astro.test.hafidh.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseData(
    @SerialName("login")
    val login: String? = null,
    @SerialName("id")
    val id: Long? = null,
    @SerialName("avatar_url")
    val avatar_url: String? = null,
    @SerialName("score")
    val score: Float? = null
)