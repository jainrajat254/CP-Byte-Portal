package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EditPasswordRequest(
    val oldPass: String,
    val newPass: String,
    val confPass: String
)
