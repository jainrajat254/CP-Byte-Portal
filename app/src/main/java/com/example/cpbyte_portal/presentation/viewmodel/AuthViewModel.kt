package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.domain.repository.LoginRepository
import com.example.cpbyte_portal.domain.service.ApiService
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthViewModel(): ViewModel(),KoinComponent {

    private val loginRepository: LoginRepository by inject()

    private val _loginState = MutableStateFlow<ResultState<LoginResponse>>(ResultState.Loading)
    val loginState: StateFlow<ResultState<LoginResponse>> = _loginState

    fun loginUser(email: String, password: String) {
        _loginState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val loginResponse: LoginResponse = loginRepository.login(loginRequest)
                _loginState.value = ResultState.Success(loginResponse)
            } catch (e: Exception) {
                _loginState.value=ResultState.Failure(e)
            }
        }
    }

}