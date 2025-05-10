package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse
import com.example.cpbyte_portal.domain.model.UpdateStatusRequest
import com.example.cpbyte_portal.domain.model.UpdateStatusResponse

interface CoordinatorRepository {

    suspend fun fetchAllAttendance(): FetchAttendanceResponse

    suspend fun membersOfDomain(domain: String): DomainUsersResponse

    suspend fun markAttendance(markAttendance: MarkAttendance): MarkAttendanceResponse

    suspend fun checkStatus(checkStatusRequest: CheckStatusRequest): CheckStatusResponse

    suspend fun updateStatus(updateStatusRequest: UpdateStatusRequest): UpdateStatusResponse
}