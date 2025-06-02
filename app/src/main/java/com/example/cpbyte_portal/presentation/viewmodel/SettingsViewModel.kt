package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.AvatarRequest
import com.example.cpbyte_portal.domain.model.AvatarResponse
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.domain.model.EditPasswordResponse
import com.example.cpbyte_portal.domain.repository.SettingsRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    private val _editPasswordState =
        MutableStateFlow<ResultState<EditPasswordResponse>>(ResultState.Idle)
    val editPasswordState: StateFlow<ResultState<EditPasswordResponse>> = _editPasswordState

    private val _editAvatarState =
        MutableStateFlow<ResultState<AvatarResponse>>(ResultState.Idle)
    val editAvatarState: StateFlow<ResultState<AvatarResponse>> = _editAvatarState

    fun editPassword(editPasswordRequest: EditPasswordRequest) {
        _editPasswordState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val editPasswordResponse: EditPasswordResponse =
                    settingsRepository.editPassword(editPassword = editPasswordRequest)
                _editPasswordState.value = ResultState.Success(editPasswordResponse)
            } catch (e: Exception) {
                _editPasswordState.value = ResultState.Failure(e)
            }
        }
    }

    fun editAvatar(avatarRequest: AvatarRequest) {
        _editAvatarState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val editAvatarStateResponse: AvatarResponse =
                    settingsRepository.editAvatar(avatarRequest = avatarRequest)
                _editAvatarState.value = ResultState.Success(editAvatarStateResponse)
            } catch (e: Exception) {
                _editAvatarState.value = ResultState.Failure(e)
            }
        }
    }

    fun clear() {
        _editPasswordState.value = ResultState.Idle
        _editAvatarState.value = ResultState.Idle
    }
}