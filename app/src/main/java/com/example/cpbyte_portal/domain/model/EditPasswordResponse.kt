package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EditPasswordResponse(
    val success: Boolean? = true,
    val message: String
)
