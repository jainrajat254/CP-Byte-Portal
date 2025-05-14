package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddEventResponse(
    val createdAt: String? = null,
    val date: String? = null,
    val events: List<Event>? = null,
    val id: String? = null,
    val updateAt: String? = null
)
