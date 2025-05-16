package com.example.cpbyte_portal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
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

    fun fetchUserProfile() {
        _profileState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val getUserProfileResponse: ProfileResponse = userRepository.getProfile()
                _profileState.value = ResultState.Success(getUserProfileResponse)
                Log.d("UserProfile", "UserProfile: $getUserProfileResponse")

                sharedPrefsManager.saveProfile(getUserProfileResponse)
            } catch (e: Exception) {
                Log.e("UserProfile", "Failed to fetch profile", e)
                _profileState.value = ResultState.Failure(e)
            }
        }
    }
}