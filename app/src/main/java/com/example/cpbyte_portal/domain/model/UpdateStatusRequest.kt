package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateStatusRequest(
    val date: String,
    val domain: String
)