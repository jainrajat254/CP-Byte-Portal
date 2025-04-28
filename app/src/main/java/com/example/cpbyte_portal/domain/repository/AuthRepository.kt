package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.model.LogoutResponse

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest):LoginResponse

    suspend fun logout(): LogoutResponse
}