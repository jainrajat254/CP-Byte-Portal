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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    private val _getEventState = MutableStateFlow<ResultState<List<EventsResponse>>>(ResultState.Idle)
    val getEventState: StateFlow<ResultState<List<EventsResponse>>> = _getEventState.asStateFlow()

    private val _addEventState = MutableStateFlow<ResultState<AddEventResponse>>(ResultState.Idle)
    val addEventState: StateFlow<ResultState<AddEventResponse>> = _addEventState.asStateFlow()

    private val _removeEventState = MutableStateFlow<ResultState<RemoveEventResponse>>(ResultState.Idle)
    val removeEventState: StateFlow<ResultState<RemoveEventResponse>> = _removeEventState.asStateFlow()

    fun getEvents(month: String) {
        _getEventState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val response = eventRepository.getAllEvents(month)
                _getEventState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _getEventState.value = ResultState.Failure(Exception("Could not load events."))
            }
        }
    }

    fun addEvent(request: AddEventRequest) {
        _addEventState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val response = eventRepository.addEvent(request)
                _addEventState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _addEventState.value = ResultState.Failure(Exception("Failed to add event."))
            }
        }
    }

    fun removeEvent(eventId: String) {
        _removeEventState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val response = eventRepository.removeEvent(RemoveEventRequest(eventId))
                _removeEventState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _removeEventState.value = ResultState.Failure(Exception("Could not delete event."))
            }
        }
    }

    fun resetAddEventState() {
        _addEventState.value = ResultState.Idle
    }

    fun resetRemoveEventState() {
        _removeEventState.value = ResultState.Idle
    }

    fun clear() {
        _getEventState.value = ResultState.Idle
        _addEventState.value = ResultState.Idle
        _removeEventState.value = ResultState.Idle
    }
}
