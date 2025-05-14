package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.AvatarRequest
import com.example.cpbyte_portal.domain.model.AvatarResponse
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.domain.model.EditPasswordResponse
import com.example.cpbyte_portal.domain.repository.SettingsRepository
import com.example.cpbyte_portal.domain.service.ApiService

class SettingsRepositoryImpl(private val apService: ApiService) : SettingsRepository {

    override suspend fun editPassword(editPassword: EditPasswordRequest): EditPasswordResponse {
        return apService.editPassword(editPassword = editPassword)
    }

    override suspend fun editAvatar(avatarRequest: AvatarRequest): AvatarResponse {
        return apService.editAvatar(avatarRequest = avatarRequest)
    }
}