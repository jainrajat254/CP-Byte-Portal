package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
import com.example.cpbyte_portal.domain.model.UserProjectsResponse

interface UserRepository {

    suspend fun getUserAttendance(): UserAttendanceResponse

    suspend fun getProfile(): ProfileResponse

    suspend fun getProjects(): UserProjectsResponse
}