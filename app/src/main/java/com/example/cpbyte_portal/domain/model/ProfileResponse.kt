package com.example.cpbyte_portal.domain.model

data class ProfileResponse(
    val profileData: ProfileData,
    val message: String,
    val success: Boolean
)