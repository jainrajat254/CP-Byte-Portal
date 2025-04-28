package com.example.cpbyte_portal.domain.repository

import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.domain.model.AddEventResponse
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.domain.model.RemoveEventRequest
import com.example.cpbyte_portal.domain.model.RemoveEventResponse

interface EventRepository {

    suspend fun getAllEvents(month: String): List<EventsResponse>

    suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse

    suspend fun removeEvent(removeEventRequest: RemoveEventRequest): RemoveEventResponse
}