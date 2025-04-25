package com.example.cpbyte_portal.domain.service

import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.domain.model.EditPasswordResponse
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.model.LogoutResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse
import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.RemoveEventRequest
import com.example.cpbyte_portal.domain.model.RemoveEventResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse


interface ApiService {

    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun logout(token: String): LogoutResponse

    suspend fun fetchAllAttendance(token: String): FetchAttendanceResponse

    suspend fun membersOfDomain(token: String, domain: String): DomainUsersResponse

    suspend fun markAttendance(
        token: String,
        markAttendance: MarkAttendance,
    ): MarkAttendanceResponse

    suspend fun getAllEvents(token: String, month: String): List<EventsResponse>

    suspend fun addEvent(token: String, addEventRequest: AddEventRequest): AddEventResponse

    suspend fun removeEvent(
        token: String,
        removeEventRequest: RemoveEventRequest,
    ): RemoveEventResponse

    suspend fun editPassword(token: String, editPassword: EditPasswordRequest): EditPasswordResponse

    //Edit Avatar need to be implemented

    //data not yet specified need to check it and change the model if needed
    suspend fun getUserAttendance(token: String): UserAttendanceResponse

    suspend fun getProfile(token: String): ProfileResponse
}

//instead of passing token again and again we can use request interceptor
// that will set the token once during client creation and automatically attach it to every request.
//will later implement in di -> NetworkModule



