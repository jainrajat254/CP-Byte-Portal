package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val category: String,
    val createdAt: String? = null,
    val discription: String,
    val id: String,
    val scheduleId: String? = null,
    val title: String,
    val updatedAt: String? = null,
)