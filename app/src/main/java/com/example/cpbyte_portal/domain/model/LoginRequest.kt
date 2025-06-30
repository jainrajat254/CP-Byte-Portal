package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    val library_id: String,
    val password: String,
)