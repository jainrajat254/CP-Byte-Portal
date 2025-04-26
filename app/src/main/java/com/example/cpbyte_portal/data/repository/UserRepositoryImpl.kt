package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
import com.example.cpbyte_portal.domain.repository.UserRepository
import com.example.cpbyte_portal.domain.service.ApiService

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun getUserAttendance(): UserAttendanceResponse {
        return apiService.getUserAttendance()
    }

    override suspend fun getProfile(): ProfileResponse {
        return apiService.getProfile()
    }
}