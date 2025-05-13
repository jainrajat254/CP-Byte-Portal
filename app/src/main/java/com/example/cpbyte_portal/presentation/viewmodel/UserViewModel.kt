package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.ProfileResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
import com.example.cpbyte_portal.domain.repository.UserRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _getUserAttendanceState =
        MutableStateFlow<ResultState<UserAttendanceResponse>>(ResultState.Idle)
    val getUserAttendanceState: StateFlow<ResultState<UserAttendanceResponse>> =
        _getUserAttendanceState

    private val _getUserProfileState =
        MutableStateFlow<ResultState<ProfileResponse>>(ResultState.Idle)
    val getUserProfileState: StateFlow<ResultState<ProfileResponse>> =
        _getUserProfileState

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

    fun getProfile() {
        _getUserProfileState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val getUserProfileResponse: ProfileResponse =
                    userRepository.getProfile()
                _getUserProfileState.value = ResultState.Success(getUserProfileResponse)
            } catch (e: Exception) {
                _getUserProfileState.value = ResultState.Failure(e)
            }
        }
    }
}