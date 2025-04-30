package com.example.cpbyte_portal.domain.model

data class ProfileData(
    val attendances: List<Any>,
    val avatar: Any,
    val createdAt: String,
    val devAttendance: Int,
    val domain_dev: String,
    val domain_dsa: String,
    val dsaAttendance: Int,
    val email: String,
    val id: String,
    val library_id: String,
    val mentor_dev: String? = null,
    val mentor_dsa: String? = null,
    val name: String,
    val role: String,
    val updatedAt: String,
    val year: Int,
)