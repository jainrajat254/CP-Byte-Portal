package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProgressTrackerViewCard(
    title: String,
    totalQuestions: String,
    color: Color,
    arr: IntArray
) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .padding(end = 10.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF2D2D2D),
                shape = RoundedCornerShape(14.dp)
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (arr.isEmpty() || title != "Heatmap") {
                Text(
                    text = totalQuestions,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    arr.take(5).forEach { num ->
                        BoxForHeatmap(if (num > 0) Color(0xFF65E26A) else Color(0xFFE45D5D))
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun BoxForHeatmap(color: Color) {
    Card(
        modifier = Modifier.size(10.dp),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {}
}
