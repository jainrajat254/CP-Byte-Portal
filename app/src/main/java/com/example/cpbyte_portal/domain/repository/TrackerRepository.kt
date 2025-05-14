package com.example.cpbyte_portal.domain.repository

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

interface TrackerRepository {

    suspend fun getUserDashboard(libraryId: String): UserDashboardResponse

    suspend fun addLeetCode(leetCodeUsername: AddLeetCodeRequest): AddLeetCodeResponse

    suspend fun addGithub(githubUsername: AddGithubRequest): Github

    suspend fun refreshAll(): RefreshResponse

    suspend fun addSkill(addSkillRequest: SkillRequest): SkillResponse

    suspend fun removeSkill(removeSkillRequest: SkillRequest): SkillResponse

    suspend fun addProject(addProjectRequest: AddProjectRequest): ProjectResponse

    suspend fun removeProject(projectId: String): List<ProjectResponse>
}