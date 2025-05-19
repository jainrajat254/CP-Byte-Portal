package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    unfocusedContainerColor = Color(0xFF1E293B),       // Dark blue-gray background
    focusedContainerColor = Color(0xFF2B4D77),         // Slightly lighter focused shade for subtle focus effect
    disabledContainerColor = Color(0xFF1E293B),        // Keep disabled fields consistent
    focusedIndicatorColor = Color.Transparent,         // No underline
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    disabledTextColor = Color.Gray,
    focusedPlaceholderColor = Color(0xFFB0BEC5),       // Soft gray-blue for placeholder
    unfocusedPlaceholderColor = Color(0xFF90A4AE),     // Lighter variant for unfocused
    disabledPlaceholderColor = Color.DarkGray,
    cursorColor = Color(0xFF00CFFD)                    // Accent cyan for cursor
)
