package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse

interface CoordinatorRepository {

    suspend fun fetchAllAttendance(): FetchAttendanceResponse

    suspend fun membersOfDomain(domain: String): DomainUsersResponse

    suspend fun markAttendance(markAttendance: MarkAttendance): MarkAttendanceResponse
}