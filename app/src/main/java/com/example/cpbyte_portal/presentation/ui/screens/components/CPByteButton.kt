package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CPByteButton(
    value: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF5A55FF)
        ),
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF121212)) //Button with gradient background
            .height(44.dp)
            .width(280.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation() // Adds a shadow, button looks elevated
    ) {
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}