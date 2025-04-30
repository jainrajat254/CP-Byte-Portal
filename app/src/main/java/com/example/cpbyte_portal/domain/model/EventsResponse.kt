package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EventsResponse(
    val date: String,
    val events: List<Event>
)

@Serializable
data class Event(
    val id: String,
    val content: String,
    val scheduleId: String,
    val createdAt: String,
    val updatedAt: String
)
