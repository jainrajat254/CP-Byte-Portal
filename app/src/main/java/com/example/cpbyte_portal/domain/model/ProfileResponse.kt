package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val data: ProfileData,
    val message: String,
    val success: Boolean
)