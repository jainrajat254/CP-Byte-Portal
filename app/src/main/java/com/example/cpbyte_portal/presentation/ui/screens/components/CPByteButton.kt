//package com.example.cpbyte_portal.presentation.ui.screens.components

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CPByteButton(
    value: String,
    onClick: () -> Unit // no input, returns nothing
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF5A55FF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF121212)) //button with gradient background
            .height(50.dp)
            .width(280.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation() // button looks elevated
    ) {
        Text(
            text = value,
            fontSize = 20.sp
        )
    }

}