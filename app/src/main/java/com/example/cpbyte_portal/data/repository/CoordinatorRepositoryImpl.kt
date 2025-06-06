package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse
import com.example.cpbyte_portal.domain.model.UpdateStatusRequest
import com.example.cpbyte_portal.domain.model.UpdateStatusResponse
import com.example.cpbyte_portal.domain.repository.CoordinatorRepository
import com.example.cpbyte_portal.domain.service.ApiService

class CoordinatorRepositoryImpl(private val apiService: ApiService) : CoordinatorRepository {
    override suspend fun fetchAllAttendance(): FetchAttendanceResponse {
        return apiService.fetchAllAttendance()
    }

    override suspend fun membersOfDomain(domain: String): DomainUsersResponse {
        return apiService.membersOfDomain(domain = domain)
    }

    override suspend fun markAttendance(markAttendance: MarkAttendance): MarkAttendanceResponse {
        return apiService.markAttendance(markAttendance = markAttendance)
    }

    override suspend fun checkStatus(checkStatusRequest: CheckStatusRequest): CheckStatusResponse {
        return apiService.checkStatus(checkStatusRequest = checkStatusRequest)
    }

    override suspend fun updateStatus(updateStatusRequest: UpdateStatusRequest): UpdateStatusResponse {
        return apiService.updateStatus(updateStatusRequest = updateStatusRequest)
    }
}