package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponse(
    val message: String,
    val success: String
)