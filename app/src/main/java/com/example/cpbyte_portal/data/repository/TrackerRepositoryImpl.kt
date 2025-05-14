package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.AddGithubRequest
import com.example.cpbyte_portal.domain.model.AddLeetCodeRequest
import com.example.cpbyte_portal.domain.model.AddLeetCodeResponse
import com.example.cpbyte_portal.domain.model.AddProjectRequest
import com.example.cpbyte_portal.domain.model.Github
import com.example.cpbyte_portal.domain.model.ProjectResponse
import com.example.cpbyte_portal.domain.model.RefreshResponse
import com.example.cpbyte_portal.domain.model.SkillRequest
import com.example.cpbyte_portal.domain.model.SkillResponse
import com.example.cpbyte_portal.domain.model.UserDashboardResponse
import com.example.cpbyte_portal.domain.repository.TrackerRepository
import com.example.cpbyte_portal.domain.service.ApiService

class TrackerRepositoryImpl(private val apiService: ApiService) : TrackerRepository {

    override suspend fun getUserDashboard(libraryId: String): UserDashboardResponse {
        return apiService.getUserDashboard(libraryId = libraryId)
    }

    override suspend fun addLeetCode(leetCodeUsername: AddLeetCodeRequest): AddLeetCodeResponse {
        return apiService.addLeetCode(leetCodeUsername = leetCodeUsername)
    }

    override suspend fun addGithub(githubUsername: AddGithubRequest): Github {
        return apiService.addGithub(githubUsername = githubUsername)
    }

    override suspend fun refreshAll(): RefreshResponse {
        return apiService.refreshAll()
    }

    override suspend fun addSkill(addSkillRequest: SkillRequest): SkillResponse {
        return apiService.addSkill(addSkillRequest = addSkillRequest)
    }

    override suspend fun removeSkill(removeSkillRequest: SkillRequest): SkillResponse {
        return apiService.removeSkill(removeSkillRequest = removeSkillRequest)
    }

    override suspend fun addProject(addProjectRequest: AddProjectRequest): ProjectResponse {
        return apiService.addProject(addProjectRequest = addProjectRequest)
    }

    override suspend fun removeProject(projectId: String): List<ProjectResponse> {
        return apiService.removeProject(projectId = projectId)
    }
}