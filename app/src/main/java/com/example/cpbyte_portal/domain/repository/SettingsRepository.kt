package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.AvatarRequest
import com.example.cpbyte_portal.domain.model.AvatarResponse
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.domain.model.EditPasswordResponse

interface SettingsRepository {

    suspend fun editPassword(editPassword: EditPasswordRequest): EditPasswordResponse

    suspend fun editAvatar(avatarRequest: AvatarRequest): AvatarResponse
}