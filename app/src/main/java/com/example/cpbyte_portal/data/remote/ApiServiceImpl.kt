package com.example.cpbyte_portal.data.remote

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
import com.example.cpbyte_portal.domain.service.ApiService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class ApiServiceImpl(private val client: HttpClient) : ApiService {
    private val BASE_URL = "https://cpbyte-student-portal.onrender.com/api"

    //Login Impl.
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return client.post("$BASE_URL/v1/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }

    //Logout Impl.
    override suspend fun logout(token: String): LogoutResponse {
        return client.get("$BASE_URL/v1/auth/logout") {
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }

    //fetch All Attendance Impl.
    override suspend fun fetchAllAttendance(token: String): FetchAttendanceResponse {
        return client.get("$BASE_URL/v1/coordinator/attendance") {
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }


    //Mark Attendance Impl.
    override suspend fun markAttendance(
        token: String,
        markAttendance: MarkAttendance,
    ): MarkAttendanceResponse {
        return client.post("$BASE_URL/v1/coordinator/markAttendance") {
            contentType(ContentType.Application.Json)
            setBody(markAttendance)
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }


    //fetch members of domain
    override suspend fun membersOfDomain(token: String, domain: String): DomainUsersResponse {
        return client.get("$BASE_URL/v1/coordinator/memberOfDomain") {
            url {
                parameters.append("domain", domain)
            }
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }

    //Get All Events
    //date should be in -> yyyy-mm format
    override suspend fun getAllEvents(token: String, month: String): List<EventsResponse> {
        return client.get("$BASE_URL/v1/schedule/monthEvents") {
            url {
                parameters.append("month", month)
            }
            headers {
                append("Authorization", "Bearer $token")
            }

        }.body()
    }

    //Add Event
    override suspend fun addEvent(
        token: String,
        addEventRequest: AddEventRequest,
    ): AddEventResponse {
        return client.post("$BASE_URL/v1/schedule/addEvent") {
            contentType(ContentType.Application.Json)
            setBody(addEventRequest)
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }

    //Remove Event
    override suspend fun removeEvent(
        token: String,
        removeEventRequest: RemoveEventRequest,
    ): RemoveEventResponse {
        return client.post("$BASE_URL/v1/schedule/removeEvent") {
            contentType(ContentType.Application.Json)
            setBody(removeEventRequest)
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }

    //edit password
    override suspend fun editPassword(
        token: String,
        editPassword: EditPasswordRequest,
    ): EditPasswordResponse {
        return client.post("$BASE_URL/v1/settings/editPass") {
            contentType(ContentType.Application.Json)
            setBody(editPassword)
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }

    //Edit Avatar

    //GetUserAttendance
    override suspend fun getUserAttendance(token: String): UserAttendanceResponse {
        return client.get("$BASE_URL/v1/user/attendance") {
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }

    //GetProfile
    override suspend fun getProfile(token: String): ProfileResponse {
        return client.get("$BASE_URL/v1/user/getProfile") {
            headers {
                append("Authorization", "Bearer $token")
            }
        }.body()
    }
}
