package com.example.cpbyte_portal.domain.service

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
    suspend fun editAvatar(avatarRequest: AvatarRequest): AvatarResponse
    suspend fun getUserAttendance(): UserAttendanceResponse
    suspend fun getProfile(): ProfileResponse
    suspend fun getProjects(): UserProjectsResponse
    suspend fun getUserDashboard(libraryId: String): UserDashboardResponse
    suspend fun addLeetCode(leetCodeUsername: AddLeetCodeRequest): AddLeetCodeResponse
    suspend fun addGithub(githubUsername: AddGithubRequest): Github
    suspend fun refreshAll(): RefreshResponse
    suspend fun addSkill(addSkillRequest: SkillRequest): SkillResponse
    suspend fun removeSkill(removeSkillRequest: SkillRequest): SkillResponse
    suspend fun addProject(addProjectRequest: AddProjectRequest): ProjectResponse
    suspend fun removeProject(projectId: String): List<ProjectResponse>
    suspend fun getAll(): List<Leaderboard>
}