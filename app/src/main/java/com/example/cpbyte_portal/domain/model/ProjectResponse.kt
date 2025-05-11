package com.example.cpbyte_portal.domain.model

data class ProjectResponse(
    val coverImage: String,
    val createdAt: String,
    val description: String,
    val githubUrl: String,
    val id: String,
    val projectName: String,
    val trackerId: String,
    val updatedAt: String,
    val websiteUrl: Any
)