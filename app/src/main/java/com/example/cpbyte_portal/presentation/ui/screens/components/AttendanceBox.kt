package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//@Preview(showBackground = true)
fun AttendanceBox(percentage: String, title: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF002147)),
        modifier = Modifier
            .height(63.dp)
            .width(130.dp),
        shape = RoundedCornerShape(9.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 24.dp),
        border = BorderStroke(2.dp, Color.Gray)

    ) {
        Column(


        ) {
            Text(
                text = percentage, color = Color.White,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 0.dp, 0.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title, color = Color.Gray,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 0.dp, 0.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
                fontSize = 13.sp
            )
        }

    }
}
