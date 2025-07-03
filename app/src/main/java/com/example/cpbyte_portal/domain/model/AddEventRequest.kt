package com.example.cpbyte_portal.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AddEventRequest(
    val date: String,
    val category: String,
    val title: String,
    val discription: String,
)