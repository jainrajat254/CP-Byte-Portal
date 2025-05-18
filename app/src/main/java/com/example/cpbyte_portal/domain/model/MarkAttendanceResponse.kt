package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MarkAttendanceResponse(
    val success: Boolean,
    val message: String,
)
