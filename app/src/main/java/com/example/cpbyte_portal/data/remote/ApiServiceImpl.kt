package com.example.cpbyte_portal.data.remote

import android.util.Log
import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.AddGithubRequest
import com.example.cpbyte_portal.domain.model.AddLeetCodeRequest
import com.example.cpbyte_portal.domain.model.AddLeetCodeResponse
import com.example.cpbyte_portal.domain.model.AddProjectRequest
import com.example.cpbyte_portal.domain.model.AvatarRequest
import com.example.cpbyte_portal.domain.model.AvatarResponse
import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.domain.model.EditPasswordResponse
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.Github
import com.example.cpbyte_portal.domain.model.Leaderboard
import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.model.LogoutResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse
import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.ProjectResponse
import com.example.cpbyte_portal.domain.model.RefreshResponse
import com.example.cpbyte_portal.domain.model.RemoveEventRequest
import com.example.cpbyte_portal.domain.model.RemoveEventResponse
import com.example.cpbyte_portal.domain.model.SkillRequest
import com.example.cpbyte_portal.domain.model.SkillResponse
import com.example.cpbyte_portal.domain.model.UpdateStatusRequest
import com.example.cpbyte_portal.domain.model.UpdateStatusResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
import com.example.cpbyte_portal.domain.model.UserDashboardResponse
import com.example.cpbyte_portal.domain.model.UserProjectsResponse
import com.example.cpbyte_portal.domain.service.ApiService
import com.example.cpbyte_portal.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    private val baseURL = Constants.BASE_URL

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return client.post("$baseURL/v1/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }

    override suspend fun logout(): LogoutResponse {
        return client.get("$baseURL/v1/auth/logout").body()
    }

    override suspend fun fetchAllAttendance(): FetchAttendanceResponse {
        return client.get("$baseURL/v1/coordinator/attendance").body()
    }

    override suspend fun markAttendance(markAttendance: MarkAttendance): MarkAttendanceResponse {
        return client.post("$baseURL/v1/coordinator/markAttendance") {
            contentType(ContentType.Application.Json)
            setBody(markAttendance)
        }.body()
    }

    override suspend fun membersOfDomain(domain: String): DomainUsersResponse {
        return client.get("$baseURL/v1/coordinator/memberOfDomain") {
            url { parameters.append("domain", domain) }
        }.body()
    }


    override suspend fun checkStatus(checkStatusRequest: CheckStatusRequest): CheckStatusResponse {
        return client.post("$baseURL/v1/coordinator/checkStatus") {
            contentType(ContentType.Application.Json)
            setBody(checkStatusRequest)
        }.body()
    }

    override suspend fun updateStatus(updateStatusRequest: UpdateStatusRequest): UpdateStatusResponse {
        return client.post("$baseURL/v1/coordinator/updateStatus") {
            contentType(ContentType.Application.Json)
            setBody(updateStatusRequest)
        }.body()
    }

    //month should be like -> YYYY-MM
    override suspend fun getAllEvents(month: String): List<EventsResponse> {
        return client.get("$baseURL/v1/schedule/monthEvents") {
            url { parameters.append("month", month) }
        }.body()
    }

    override suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse {
        val response = client.post("$baseURL/v1/schedule/addEvent") {
            contentType(ContentType.Application.Json)
            setBody(addEventRequest)
        }

        val responseBody = response.bodyAsText()
        Log.d("Add EVENT RESPONSE", responseBody)

        // ✅ Check if body is empty or invalid before deserializing
        if (responseBody.isBlank()) {
            throw IllegalStateException("Empty response from server")
        }

        return Json.decodeFromString<AddEventResponse>(responseBody)
    }


    override suspend fun removeEvent(removeEventRequest: RemoveEventRequest): RemoveEventResponse {
        return client.post("$baseURL/v1/schedule/removeEvent") {
            contentType(ContentType.Application.Json)
            setBody(removeEventRequest)
        }.body()
    }

    override suspend fun editPassword(editPassword: EditPasswordRequest): EditPasswordResponse {
        return client.post("$baseURL/v1/settings/editPass") {
            contentType(ContentType.Application.Json)
            setBody(editPassword)
        }.body()
    }

    override suspend fun editAvatar(avatarRequest: AvatarRequest): AvatarResponse {
        return client.post("$baseURL/v1/settings/editAvatar") {
            contentType(ContentType.Application.Json)
            setBody(avatarRequest)
        }.body()
    }

    override suspend fun getUserAttendance(): UserAttendanceResponse {
        return client.get("$baseURL/v1/user/attendance").body()
    }

    override suspend fun getProfile(): ProfileResponse {
        return client.get("$baseURL/v1/user/getProfile").body()
    }

    override suspend fun getProjects(): UserProjectsResponse {
        return client.get("$baseURL/v1/user/getProjects").body()
    }

    override suspend fun getUserDashboard(libraryId: String): UserDashboardResponse {
        return client.get("$baseURL/v1/Tracker/getUserTrackerDashboard/$libraryId").body()
    }

    override suspend fun addLeetCode(leetCodeUsername: AddLeetCodeRequest): AddLeetCodeResponse {
        return client.post("$baseURL/v1/Tracker/addLeetCode") {
            contentType(ContentType.Application.Json)
            setBody(leetCodeUsername)
        }.body()
    }

    override suspend fun addGithub(githubUsername: AddGithubRequest): Github {
        return client.post("$baseURL/v1/Tracker/addGithub") {
            contentType(ContentType.Application.Json)
            setBody(githubUsername)
        }.body()
    }

    override suspend fun refreshAll(): RefreshResponse {
        return client.post("$baseURL/v1/Tracker/refreshAll") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun addSkill(addSkillRequest: SkillRequest): SkillResponse {
        return client.patch("$baseURL/v1/Tracker/addSkill") {
            contentType(ContentType.Application.Json)
            setBody(addSkillRequest)
        }.body()
    }

    override suspend fun removeSkill(removeSkillRequest: SkillRequest): SkillResponse {
        return client.patch("$baseURL/v1/Tracker/removeSkill") {
            contentType(ContentType.Application.Json)
            setBody(removeSkillRequest)
        }.body()
    }

    override suspend fun addProject(addProjectRequest: AddProjectRequest): ProjectResponse {
        return client.patch("$baseURL/v1/Tracker/addProject") {
            contentType(ContentType.Application.Json)
            setBody(addProjectRequest)
        }.body()
    }

    override suspend fun removeProject(projectId: String): List<ProjectResponse> {
        return client.patch("$baseURL/v1/Tracker/removeProject") {
            url { parameters.append("projectId", projectId) }
        }.body()
    }

    override suspend fun getAll(): List<Leaderboard> {
        return client.get("$baseURL/v1/Tracker/getAll").body()
    }
}