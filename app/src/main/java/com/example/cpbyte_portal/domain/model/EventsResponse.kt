package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EventsResponse(
    val date: String,
    val events: List<Event>,
)