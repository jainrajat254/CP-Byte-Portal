package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.repository.LoginRepository
import com.example.cpbyte_portal.domain.service.ApiService

class LoginRepositoryImpl(private val apiService: ApiService) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }
}