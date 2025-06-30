package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddLeetCodeResponse(
    val createdAt: String,
    val id: String,
    val leetcode: LeetCode,
    val past5: List<Int>,
    val rank: Int,
    val skills: List<String>,
    val updatedAt: String,
    val userId: String,
)
