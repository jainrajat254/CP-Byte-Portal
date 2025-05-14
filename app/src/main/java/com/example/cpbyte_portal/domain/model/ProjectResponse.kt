package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponse(
    val id: String? = null,
    val coverImage: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val description: String? = null,
    val githubUrl: String? = null,
    val projectName: String? = null,
    val trackerId: String? = null,
    val websiteUrl: String? = null
)
