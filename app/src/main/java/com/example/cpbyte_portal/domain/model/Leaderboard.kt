package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Leaderboard (
    val avatar: String? = null,
    val id: String,
    val language: String,
    val library_id: String,
    val name: String,
    val previous: List<Int>,
    val rank: Int,
    val solvedProblems: Int,
    val year: Int,
    val leetcodeUsername: String = "ishubhgoyal"
)