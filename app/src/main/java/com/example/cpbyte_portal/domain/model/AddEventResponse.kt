package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddEventResponse(
    val id: String,
    val date: String,
    val createdAt: String,
    val updatedAt: String,
)
