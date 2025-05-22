package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponse(
    val id: String,
    val coverImage: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val description: String,
    val githubUrl: String,
    val projectName: String,
    val trackerId: String? = null,
    val websiteUrl: String? = null
)
