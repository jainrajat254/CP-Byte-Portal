package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest):LoginResponse
}