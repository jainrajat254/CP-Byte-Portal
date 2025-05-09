package com.example.cpbyte_portal.presentation.ui.screens.components

import java.time.LocalDate
import java.time.LocalTime



data class EventData (
    val title: String,
    val description: String,
    val category: String,
    val date: LocalDate = LocalDate.now(),
    )