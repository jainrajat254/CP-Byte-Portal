package com.example.cpbyte_portal.domain.model

data class LeetCode(
    val calendar: String,
    val createdAt: String,
    val easy: Int,
    val hard: Int,
    val id: String,
    val medium: Int,
    val solvedProblems: Int,
    val trackerId: String,
    val updatedAt: String,
    val url: String,
    val username: String
)
