package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val category: String,
    val createdAt: String,
    val discription: String,
    val id: String,
    val scheduleId: String,
    val title: String,
    val updatedAt: String
)