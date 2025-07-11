package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Github(
    val contributions: Int,
    val createdAt: String,
    val id: String,
    val prs: Int,
    val repos: Int,
    val trackerId: String,
    val updatedAt: String,
    val url: String,
    val username: String,
)
