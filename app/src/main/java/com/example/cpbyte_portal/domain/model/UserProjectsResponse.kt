package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProjectsResponse(
    val data: Data,
    val message: String,
    val success: Boolean
)