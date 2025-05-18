package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DomainUsersResponse(
    val success: Boolean,
    val message: String,
    val data: List<DomainUser>? = null
)

@Serializable
data class DomainUser(
    val id: String,
    val name: String,
    val library_id: String,
    val email: String,
    val avatar: String? = null,
    val role: String,
    val domain_dev: String,
    val domain_dsa: String,
    val dsaAttendance: Int,
    val devAttendance: Int,
    val mentor_dev: String? = null,
    val mentor_dsa: String? = null,
    val year: Int,
    val password: String,
    val createdAt: String,
    val updatedAt: String,
    val status: String,
    //Not coming from backend
    val attendanceStatus: String = "NOT_MARKED" // Local use: "PRESENT", "ABSENT", "NOT_MARKED"
)
