package com.example.cpbyte_portal.domain.model

data class UserDashboardResponse(
    val avatar: Any,
    val devAttendance: Int,
    val domain_dev: String,
    val domain_dsa: String,
    val dsaAttendance: Int,
    val email: String,
    val id: String,
    val library_id: String,
    val mentor_dev: Any,
    val mentor_dsa: Any,
    val name: String,
    val role: String,
    val tracker: Tracker,
    val year: Int
)