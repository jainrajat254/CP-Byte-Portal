package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AvatarResponse(
    val success: Boolean,
    val message: String,
    val image: String,
)
