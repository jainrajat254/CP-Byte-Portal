package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class  Tracker(
    val createdAt: String,
    val github: Github,
    val id: String,
    val leetcode: LeetCode,
    val past5: List<Int>,
    val projects: List<ProjectResponse>,
    val rank: Int,
    val skills: List<String>,
    val updatedAt: String,
    val userId: String
)
