package com.example.cpbyte_portal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
import com.example.cpbyte_portal.domain.model.UserProjectsResponse
import com.example.cpbyte_portal.domain.repository.UserRepository
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val sharedPrefsManager: SharedPrefsManager,
) : ViewModel() {

    private val _getUserAttendanceState =
        MutableStateFlow<ResultState<UserAttendanceResponse>>(ResultState.Idle)
    val getUserAttendanceState: StateFlow<ResultState<UserAttendanceResponse>> =
        _getUserAttendanceState

    private val _profileState = MutableStateFlow<ResultState<ProfileResponse>>(ResultState.Idle)
    val profileState: StateFlow<ResultState<ProfileResponse>> = _profileState

    private val _projectState =
        MutableStateFlow<ResultState<UserProjectsResponse>>(ResultState.Idle)
    val projectState: StateFlow<ResultState<UserProjectsResponse>> = _projectState

    private val _isLoadingDashboard = MutableStateFlow(false)
    private var isDashboardDataLoaded = false

    fun loadDashboardData() {
        if (!isDashboardDataLoaded && !_isLoadingDashboard.value) {
            fetchUserProfile()
        }
    }

    fun refreshDashboard() {
        isDashboardDataLoaded = false // Force reload
        fetchUserProfile()
    }

     fun fetchUserProfile() {
        _profileState.value = ResultState.Loading
        _isLoadingDashboard.value = true

        viewModelScope.launch {
            try {
                val profileResponse = userRepository.getProfile()
                sharedPrefsManager.saveProfile(profileResponse)
                _profileState.value = ResultState.Success(profileResponse)
                isDashboardDataLoaded = true
                Log.d("UserProfile", "Profile loaded successfully: $profileResponse")
            } catch (e: Exception) {
                Log.e("UserProfile", "Failed to fetch profile", e)
                _profileState.value = ResultState.Failure(e)
            } finally {
                _isLoadingDashboard.value = false
            }
        }
    }

    fun getUserAttendance() {
        _getUserAttendanceState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val getUserAttendanceResponse: UserAttendanceResponse =
                    userRepository.getUserAttendance()
                _getUserAttendanceState.value = ResultState.Success(getUserAttendanceResponse)
            } catch (e: Exception) {
                _getUserAttendanceState.value = ResultState.Failure(e)
            }
        }
    }

    fun getProjects() {
        _projectState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val getUserProjectsResponse: UserProjectsResponse =
                    userRepository.getProjects()
                _projectState.value = ResultState.Success(getUserProjectsResponse)
            } catch (e: Exception) {
                _projectState.value = ResultState.Failure(e)
            }
        }
    }

    fun clear() {
        _profileState.value = ResultState.Idle
        _getUserAttendanceState.value = ResultState.Idle
        _projectState.value = ResultState.Idle
        _isLoadingDashboard.value = false
        isDashboardDataLoaded = false
        sharedPrefsManager.clearProfile()
    }
}