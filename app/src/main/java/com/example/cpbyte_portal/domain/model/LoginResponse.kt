package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    val data: String,
    val message: String,
    val success: Boolean
)