package com.example.cpbyte_portal.domain.service

import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.model.LogoutResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse


interface ApiService {

    suspend fun login(loginRequest:LoginRequest): LoginResponse

    suspend fun logout(token: String): LogoutResponse

    suspend fun fetchAllAttendance(token: String): FetchAttendanceResponse

    suspend fun membersOfDomain(token: String, domain: String): DomainUsersResponse

    suspend fun markAttendance(token: String, markAttendance: MarkAttendance): MarkAttendanceResponse

}

//instead of passing token again and again we can use request interceptor
// that will set the token once during client creation and automatically attach it to every request.
//will later implement in di -> NetworkModule



