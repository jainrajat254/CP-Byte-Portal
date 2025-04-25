package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FetchAttendanceResponse(
    val success: Boolean,
    val message: String,
    val data: List<AttendanceData>? = null,
)

@Serializable
data class AttendanceData(
    val name: String,
    val library_id: String,
    val attendances: List<AttendanceRecord>? = null,
)


@Serializable
data class AttendanceRecord(
    val date: String,
    val status: String
)
