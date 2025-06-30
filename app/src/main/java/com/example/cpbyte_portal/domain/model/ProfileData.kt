package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    val attendances: List<AttendanceData>? = null,
    val avatar: String? = null,
    val createdAt: String? = null,
    val devAttendance: Int? = null,
    val domain_dev: String? = null,
    val domain_dsa: String? = null,
    val dsaAttendance: Int? = null,
    val email: String? = null,
    val id: String? = null,
    val library_id: String? = null,
    val mentor_dev: String? = null,
    val mentor_dsa: String? = null,
    val name: String,
    val role: String? = null,
    val updatedAt: String? = null,
    val year: Int? = null,
)
