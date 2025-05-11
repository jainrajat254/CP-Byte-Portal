package com.example.cpbyte_portal.domain.model

data class SkillResponse(
    val createdAt: String,
    val id: String,
    val past5: List<Int>,
    val rank: Int,
    val skills: List<String>,
    val updatedAt: String,
    val userId: String
)