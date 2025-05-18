package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonHeader(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(5.dp)
                .height(28.dp)
                .background(Color(0xFF00CFFD), shape = RoundedCornerShape(5.dp)) // Cyan accent
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily
        )
    }
}