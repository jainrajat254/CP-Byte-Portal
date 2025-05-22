package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddLeetCodeRequest(
    val leetcodeUsername: String
)