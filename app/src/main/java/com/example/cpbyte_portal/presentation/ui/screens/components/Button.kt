package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoordinatorButton(
    onClick: () -> Unit,
    label: String = "Action" // Default label if none provided
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd // Aligns the button inside the box to bottom-end
    ) {
        // The actual button component
        Button(
            onClick = onClick,// Action to perform when button is clicked
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF17191d),
                contentColor = Color(0xFF40C4FF)
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(2.dp)
                .height(35.dp)
                .width(115.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp) // Applies shadow to give a raised appearance
        ) {
            Text(text = label, fontSize = 17.sp )
        }
    }
}

