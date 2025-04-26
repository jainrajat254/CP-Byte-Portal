package com.example.cpbyte_portal.data.repository

import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.domain.model.RemoveEventRequest
import com.example.cpbyte_portal.domain.model.RemoveEventResponse
import com.example.cpbyte_portal.domain.repository.EventRepository
import com.example.cpbyte_portal.domain.service.ApiService

class EventRepositoryImpl(private val apiService: ApiService) : EventRepository {
    override suspend fun getAllEvents(month: String): List<EventsResponse> {
        return apiService.getAllEvents(month = month)
    }

    override suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse {
        return apiService.addEvent(addEventRequest = addEventRequest)
    }

    override suspend fun removeEvent(removeEventRequest: RemoveEventRequest): RemoveEventResponse {
        return apiService.removeEvent(removeEventRequest = removeEventRequest)
    }
}