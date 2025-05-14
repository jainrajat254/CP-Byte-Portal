package com.example.cpbyte_portal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrackerViewModel(private val trackerRepository: TrackerRepository) : ViewModel() {

    private val _getUserDashboardState =
        MutableStateFlow<ResultState<UserDashboardResponse>>(ResultState.Idle)
    val getUserDashboardState: StateFlow<ResultState<UserDashboardResponse>> =
        _getUserDashboardState

    private val _addLeetCodeState =
        MutableStateFlow<ResultState<AddLeetCodeResponse>>(ResultState.Idle)
    val addLeetCodeState: StateFlow<ResultState<AddLeetCodeResponse>> = _addLeetCodeState

    private val _addGithubState =
        MutableStateFlow<ResultState<Github>>(ResultState.Idle)
    val addGithubState: StateFlow<ResultState<Github>> = _addGithubState

    private val _refreshAllState =
        MutableStateFlow<ResultState<RefreshResponse>>(ResultState.Idle)
    val refreshAllState: StateFlow<ResultState<RefreshResponse>> = _refreshAllState

    private val _addSkillState =
        MutableStateFlow<ResultState<SkillResponse>>(ResultState.Idle)
    val addSkillState: StateFlow<ResultState<SkillResponse>> = _addSkillState

    private val _removeSkillState =
        MutableStateFlow<ResultState<SkillResponse>>(ResultState.Idle)
    val removeSkillState: StateFlow<ResultState<SkillResponse>> = _removeSkillState

    private val _addProjectState =
        MutableStateFlow<ResultState<ProjectResponse>>(ResultState.Idle)
    val addProjectState: StateFlow<ResultState<ProjectResponse>> = _addProjectState

    private val _removeProjectState =
        MutableStateFlow<ResultState<List<ProjectResponse>>>(ResultState.Idle)
    val removeProjectState: StateFlow<ResultState<List<ProjectResponse>>> = _removeProjectState

    fun getUserDashboard(libraryId: String) {
        _getUserDashboardState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val getUserDashboardResponse: UserDashboardResponse =
                    trackerRepository.getUserDashboard(libraryId = libraryId)
                _getUserDashboardState.value = ResultState.Success(getUserDashboardResponse)
            } catch (e: Exception) {
                _getUserDashboardState.value = ResultState.Failure(e)
            }
        }
    }

    fun addLeetCode(leetCodeUsername: AddLeetCodeRequest) {
        _addLeetCodeState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val addLeetCodeResponse: AddLeetCodeResponse =
                    trackerRepository.addLeetCode(leetCodeUsername = leetCodeUsername)
                _addLeetCodeState.value = ResultState.Success(addLeetCodeResponse)
            } catch (e: Exception) {
                _addLeetCodeState.value = ResultState.Failure(e)
            }
        }
    }

    fun addGithub(githubUsername: AddGithubRequest) {
        _addGithubState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val addGithubResponse: Github =
                    trackerRepository.addGithub(githubUsername = githubUsername)
                _addGithubState.value = ResultState.Success(addGithubResponse)
            } catch (e: Exception) {
                _addGithubState.value = ResultState.Failure(e)
            }
        }
    }

    fun refreshAll() {
        _refreshAllState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val refreshAllResponse: RefreshResponse =
                    trackerRepository.refreshAll()
                _refreshAllState.value = ResultState.Success(refreshAllResponse)
            } catch (e: Exception) {
                _refreshAllState.value = ResultState.Failure(e)
            }
        }
    }

    fun addSkill(addSkillRequest: SkillRequest) {
        _addSkillState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val addSkillResponse: SkillResponse =
                    trackerRepository.addSkill(addSkillRequest = addSkillRequest)
                _addSkillState.value = ResultState.Success(addSkillResponse)
            } catch (e: Exception) {
                _addSkillState.value = ResultState.Failure(e)
            }
        }
    }

    fun removeSkill(removeSkillRequest: SkillRequest) {
        _removeSkillState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val removeSkillResponse: SkillResponse =
                    trackerRepository.removeSkill(removeSkillRequest = removeSkillRequest)
                _removeSkillState.value = ResultState.Success(removeSkillResponse)
            } catch (e: Exception) {
                _removeSkillState.value = ResultState.Failure(e)
            }
        }
    }

    fun addProject(addProjectRequest: AddProjectRequest) {
        _addProjectState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val addProjectResponse: ProjectResponse =
                    trackerRepository.addProject(addProjectRequest = addProjectRequest)
                _addProjectState.value = ResultState.Success(addProjectResponse)
                Log.d("PROJECT RESPONSE","$addProjectResponse")
            } catch (e: Exception) {
                _addProjectState.value = ResultState.Failure(e)
            }
        }
    }

    fun removeProject(projectId: String) {
        _removeProjectState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val removeProjectResponse: List<ProjectResponse> =
                    trackerRepository.removeProject(projectId = projectId)
                _removeProjectState.value = ResultState.Success(removeProjectResponse)
            } catch (e: Exception) {
                _removeProjectState.value = ResultState.Failure(e)
            }
        }
    }
}