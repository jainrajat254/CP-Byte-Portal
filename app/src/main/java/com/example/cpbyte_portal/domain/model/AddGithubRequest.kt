package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddGithubRequest(
    val githubUsername: String,
)