package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.model.LogoutResponse
import com.example.cpbyte_portal.domain.repository.AuthRepository
import com.example.cpbyte_portal.domain.service.ApiService

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }

    override suspend fun logout(): LogoutResponse {
        return apiService.logout();
    }
}