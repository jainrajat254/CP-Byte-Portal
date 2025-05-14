package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.domain.model.RemoveEventRequest
import com.example.cpbyte_portal.domain.model.RemoveEventResponse
import com.example.cpbyte_portal.domain.repository.EventRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    private val _getEventState =
        MutableStateFlow<ResultState<List<EventsResponse>>>(ResultState.Idle)
    val getEventState: StateFlow<ResultState<List<EventsResponse>>> = _getEventState

    private val _addEventState = MutableStateFlow<ResultState<AddEventResponse>>(ResultState.Idle)
    val addEventState: StateFlow<ResultState<AddEventResponse>> = _addEventState

    private val _removeEventState =
        MutableStateFlow<ResultState<RemoveEventResponse>>(ResultState.Idle)
    val removeEventState: StateFlow<ResultState<RemoveEventResponse>> = _removeEventState

    fun getEvents(month: String) {
        _getEventState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val eventResponse: List<EventsResponse> = eventRepository.getAllEvents(month)
                _getEventState.value = ResultState.Success(eventResponse)
            } catch (e: Exception) {
                _getEventState.value = ResultState.Failure(e)
            }
        }
    }

    fun addEvent(addEventRequest: AddEventRequest) {
        _addEventState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val addEventResponse: AddEventResponse = eventRepository.addEvent(addEventRequest)
                _addEventState.value = ResultState.Success(addEventResponse)
            } catch (e: Exception) {
                _addEventState.value = ResultState.Failure(e)
            }
        }
    }

    fun removeEvent(eventId: String) {
        _removeEventState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val removeEventRequest = RemoveEventRequest(eventId = eventId)
                val removeEventResponse: RemoveEventResponse =
                    eventRepository.removeEvent(removeEventRequest)
                _removeEventState.value = ResultState.Success(removeEventResponse)
            } catch (e: Exception) {
                _removeEventState.value = ResultState.Failure(e)
            }
        }
    }
}