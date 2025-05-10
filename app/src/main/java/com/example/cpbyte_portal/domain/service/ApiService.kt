package com.example.cpbyte_portal.domain.service

import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
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
import com.example.cpbyte_portal.domain.model.UpdateStatusRequest
import com.example.cpbyte_portal.domain.model.UpdateStatusResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse


interface ApiService {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun logout(): LogoutResponse
    suspend fun fetchAllAttendance(): FetchAttendanceResponse
    suspend fun membersOfDomain(domain: String): DomainUsersResponse
    suspend fun markAttendance(markAttendance: MarkAttendance): MarkAttendanceResponse
    suspend fun checkStatus(checkStatusRequest: CheckStatusRequest): CheckStatusResponse
    suspend fun updateStatus(updateStatusRequest: UpdateStatusRequest): UpdateStatusResponse
    suspend fun getAllEvents(month: String): List<EventsResponse>
    suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse
    suspend fun removeEvent(removeEventRequest: RemoveEventRequest): RemoveEventResponse
    suspend fun editPassword(editPassword: EditPasswordRequest): EditPasswordResponse
    suspend fun getUserAttendance(): UserAttendanceResponse
    suspend fun getProfile(): ProfileResponse


    //only edit Avatar is left to add
}