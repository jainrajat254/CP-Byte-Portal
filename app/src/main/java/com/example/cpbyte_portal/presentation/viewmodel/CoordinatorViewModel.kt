package com.example.cpbyte_portal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.FetchAttendanceResponse
import com.example.cpbyte_portal.domain.model.UserAttendanceResponse
import com.example.cpbyte_portal.domain.repository.CoordinatorRepository
import com.example.cpbyte_portal.domain.repository.UserRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoordinatorViewModel(private val coordinatorRepository: CoordinatorRepository): ViewModel() {

    private val _attendanceState = MutableStateFlow<ResultState<FetchAttendanceResponse>>(ResultState.Idle)
    val attendaceState: StateFlow<ResultState<FetchAttendanceResponse>> = _attendanceState



    fun fetchAllAttendance() {
        _attendanceState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val userAttendanceResponse: FetchAttendanceResponse = coordinatorRepository.fetchAllAttendance()
                _attendanceState.value = ResultState.Success(userAttendanceResponse)
            } catch (e: Exception) {
                _attendanceState.value = ResultState.Failure(e)
                Log.d("VIEW MODEL",e.message.toString())
            }
        }
    }


}