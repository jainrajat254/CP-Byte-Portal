package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme

@Composable
fun SaveChangesButton(
    value: String, // Text to display inside the button
    onClick: () -> Unit // no input, returns nothing
) {
    Button(
        onClick = onClick, // Handles button clicks
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = CPByteTheme.brandBlue
        ),
        modifier = Modifier
            .fillMaxWidth()//Button with gradient background
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