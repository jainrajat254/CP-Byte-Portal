package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.model.LogoutResponse
import com.example.cpbyte_portal.domain.repository.AuthRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userViewModel: UserViewModel,
) : ViewModel() {

    private val _loginState = MutableStateFlow<ResultState<LoginResponse>>(ResultState.Idle)
    val loginState: StateFlow<ResultState<LoginResponse>> = _loginState

    private val _logoutState = MutableStateFlow<ResultState<LogoutResponse>>(ResultState.Idle)
    val logoutState: StateFlow<ResultState<LogoutResponse>> = _logoutState

    fun loginUser(libraryId: String, password: String) {
        _loginState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(library_id = libraryId, password = password)
                val loginResponse: LoginResponse = authRepository.login(loginRequest)
                _loginState.value = ResultState.Success(loginResponse)
                userViewModel.fetchUserProfile()
            } catch (e: Exception) {
                _loginState.value = ResultState.Failure(e)
            }
        }
    }

    fun logoutUser() {
        _logoutState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val logoutResponse: LogoutResponse = authRepository.logout()
                _logoutState.value = ResultState.Success(logoutResponse)
            } catch (e: Exception) {
                _logoutState.value = ResultState.Failure(e)
            }
        }
    }
}