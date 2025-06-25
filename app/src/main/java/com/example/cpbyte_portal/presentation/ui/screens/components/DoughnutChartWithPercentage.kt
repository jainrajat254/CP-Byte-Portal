package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DoughnutChartWithPercentage(
    percentage: Int,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF4CAF50),
    backgroundColor: Color = Color(0xFFF44336),
    innerCircleRadiusFraction: Float = 0.6f,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clickable { }) {
            val size = Size(size.width, size.height)
            val sweepAngle = (percentage / 100f) * 360f

            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = true,
                size = size
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = true,
                size = size
            )

            drawCircle(
                color = Color(0xFF0F172A),
                radius = size.width * innerCircleRadiusFraction / 2f,
                center = center
            )
        }

        Text(
            text = "$percentage%",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}