package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddProjectRequest(
    val project: Project,
)

@Serializable
data class Project(
    val coverImage: String,
    val description: String,
    val githubUrl: String,
    val projectName: String,
)