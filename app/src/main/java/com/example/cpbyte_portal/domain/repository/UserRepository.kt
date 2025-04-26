package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse

interface UserRepository {

    suspend fun getUserAttendance(): UserAttendanceResponse

    suspend fun getProfile(): ProfileResponse
}