package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
import com.example.cpbyte_portal.domain.model.DomainUsersResponse
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.MarkAttendanceResponse
import com.example.cpbyte_portal.domain.model.UpdateStatusRequest
import com.example.cpbyte_portal.domain.model.UpdateStatusResponse
import com.example.cpbyte_portal.domain.repository.CoordinatorRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoordinatorViewModel(private val coordinatorRepository: CoordinatorRepository) : ViewModel() {

    private val _fetchAllAttendanceState =
        MutableStateFlow<ResultState<FetchAttendanceResponse>>(ResultState.Idle)
    val fetchAllAttendanceState: StateFlow<ResultState<FetchAttendanceResponse>> =
        _fetchAllAttendanceState

    private val _membersOfDomainState =
        MutableStateFlow<ResultState<DomainUsersResponse>>(ResultState.Idle)
    val membersOfDomainState: StateFlow<ResultState<DomainUsersResponse>> = _membersOfDomainState

    private val _markAttendanceState =
        MutableStateFlow<ResultState<MarkAttendanceResponse>>(ResultState.Idle)
    val markAttendanceState: StateFlow<ResultState<MarkAttendanceResponse>> = _markAttendanceState.asStateFlow()

    private val _checkStatusState =
        MutableStateFlow<ResultState<CheckStatusResponse>>(ResultState.Idle)
    val checkStatusState: StateFlow<ResultState<CheckStatusResponse>> = _checkStatusState

    private val _updateStatusStateState =
        MutableStateFlow<ResultState<UpdateStatusResponse>>(ResultState.Idle)
    val updateStatusStateState: StateFlow<ResultState<UpdateStatusResponse>> =
        _updateStatusStateState

    fun fetchAllAttendance() {
        _fetchAllAttendanceState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val fetchAllAttendanceResponse: FetchAttendanceResponse =
                    coordinatorRepository.fetchAllAttendance()
                _fetchAllAttendanceState.value =
                    ResultState.Success(fetchAllAttendanceResponse)
            } catch (e: Exception) {
                _fetchAllAttendanceState.value = ResultState.Failure(e)
            }
        }
    }

    fun membersOfDomain(domain: String) {
        _membersOfDomainState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val membersOfDomainResponse: DomainUsersResponse =
                    coordinatorRepository.membersOfDomain(domain)
                _membersOfDomainState.value = ResultState.Success(membersOfDomainResponse)
            } catch (e: Exception) {
                _membersOfDomainState.value = ResultState.Failure(e)
            }
        }
    }

    fun markAttendance(markAttendance: MarkAttendance) {
        _markAttendanceState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val markAttendanceResponse: MarkAttendanceResponse =
                    coordinatorRepository.markAttendance(markAttendance)
                _markAttendanceState.value = ResultState.Success(markAttendanceResponse)
            } catch (e: Exception) {
                _markAttendanceState.value = ResultState.Failure(e)
            }
        }
    }

    fun checkStatus(checkStatusRequest: CheckStatusRequest) {
        _checkStatusState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val checkStatusResponse: CheckStatusResponse =
                    coordinatorRepository.checkStatus(checkStatusRequest)
                _checkStatusState.value = ResultState.Success(checkStatusResponse)
            } catch (e: Exception) {
                _checkStatusState.value = ResultState.Failure(e)
            }
        }
    }

    fun updateStatus(updateStatusRequest: UpdateStatusRequest) {
        _updateStatusStateState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val updateStatusResponse: UpdateStatusResponse =
                    coordinatorRepository.updateStatus(updateStatusRequest)
                _updateStatusStateState.value = ResultState.Success(updateStatusResponse)
            } catch (e: Exception) {
                _updateStatusStateState.value = ResultState.Failure(e)
            }
        }
    }
}