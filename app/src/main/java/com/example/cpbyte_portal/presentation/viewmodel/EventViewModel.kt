package com.example.cpbyte_portal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.Event
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.domain.model.RemoveEventRequest
import com.example.cpbyte_portal.domain.model.RemoveEventResponse
import com.example.cpbyte_portal.domain.repository.EventRepository
import com.example.cpbyte_portal.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.OffsetDateTime

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    private val _getEventState =
        MutableStateFlow<ResultState<List<EventsResponse>>>(ResultState.Idle)
    val getEventState: StateFlow<ResultState<List<EventsResponse>>> = _getEventState.asStateFlow()

    private val _addEventState = MutableStateFlow<ResultState<AddEventResponse>>(ResultState.Idle)
    val addEventState: StateFlow<ResultState<AddEventResponse>> = _addEventState.asStateFlow()

    private val _removeEventState =
        MutableStateFlow<ResultState<RemoveEventResponse>>(ResultState.Idle)
    val removeEventState: StateFlow<ResultState<RemoveEventResponse>> =
        _removeEventState.asStateFlow()

    private val _upcomingEvents = MutableStateFlow<List<Pair<LocalDate, Event>>>(emptyList())
    val upcomingEvents: StateFlow<List<Pair<LocalDate, Event>>> = _upcomingEvents


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

    fun getEventsForNext10Days() {
        viewModelScope.launch {
            val today = LocalDate.now()
            val endDate = today.plusDays(10)

            val currentMonth = today.monthValue
            val currentYear = today.year

            val nextMonth = if (today.monthValue == 12) 1 else today.monthValue + 1
            val nextMonthYear = if (today.monthValue == 12) today.year + 1 else today.year

            val monthsToFetch = listOf(
                formattedDate(currentYear, currentMonth),
                formattedDate(nextMonthYear, nextMonth)
            )

            val addedEventIds = mutableSetOf<String>()
            val filteredEvents = mutableListOf<Pair<LocalDate, Event>>()

            try {
                for (month in monthsToFetch) {
                    val response = eventRepository.getAllEvents(month)

                    for (res in response) {
                        val date = try {
                            OffsetDateTime.parse(res.date).toLocalDate()
                        } catch (e: Exception) {
                            null
                        }

                        if (date != null && !date.isBefore(today) && !date.isAfter(endDate)) {
                            for (event in res.events) {
                                if (event.id !in addedEventIds) {
                                    addedEventIds.add(event.id)
                                    filteredEvents.add(date to event)
                                }
                            }
                        }
                    }

                    _getEventState.value = ResultState.Success(response)
                }

                _upcomingEvents.value = filteredEvents
            } catch (e: Exception) {
                _getEventState.value = ResultState.Failure(Exception("Could not load events."))
                _upcomingEvents.value = emptyList()
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

    private fun formattedDate(year: Int, month: Int): String {
        return "%04d-%02d".format(year, month)
    }
}
