package com.example.cpbyte_portal.domain.model

data class AddProjectRequest(
    val project: Project,
)

data class Project(
    val coverImage: String,
    val description: String,
    val githubUrl: String,
    val projectName: String,
)