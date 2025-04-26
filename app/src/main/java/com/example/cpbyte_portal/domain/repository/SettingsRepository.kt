package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.domain.model.EditPasswordResponse

interface SettingsRepository {

    suspend fun editPassword(editPassword: EditPasswordRequest): EditPasswordResponse

}