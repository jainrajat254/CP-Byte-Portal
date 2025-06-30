package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserAttendanceResponse(
    val success: Boolean,
    val message: String,
    val data: List<AttendanceData>,
)
