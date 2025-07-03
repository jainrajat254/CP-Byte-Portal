package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MarkAttendance(
    val responses: List<AttendanceStatus>,
    val subject: String,
)

@Serializable
data class AttendanceStatus(
    val library_id: String,
    val status: String,
)
