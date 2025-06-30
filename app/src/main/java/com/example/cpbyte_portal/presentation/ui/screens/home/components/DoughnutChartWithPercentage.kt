package com.example.cpbyte_portal.presentation.ui.screens.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.AbsentColor
import com.example.cpbyte_portal.presentation.ui.theme.AbsentWithReasonColor
import com.example.cpbyte_portal.presentation.ui.theme.PresentColor

@Composable
fun DoughnutChartWithPercentage(
    percentagePresent: Int,
    percentageAbsentWithReason: Int,
    modifier: Modifier = Modifier,
    innerCircleRadiusFraction: Float = 0.60f,
) {
    val percentageAbsent = 100 - percentagePresent - percentageAbsentWithReason

    val presentSweep = (percentagePresent / 100f) * 360f
    val absentWithReasonSweep = (percentageAbsentWithReason / 100f) * 360f
    val absentSweep = (percentageAbsent / 100f) * 360f

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasSize = size
            val strokeWidth = size.width * (1 - innerCircleRadiusFraction) / 2
            val topLeft = Offset(0f + strokeWidth / 2, 0f + strokeWidth / 2)
            val arcSize = Size(canvasSize.width - strokeWidth, canvasSize.height - strokeWidth)

            var startAngle = -90f

            // Present
            drawArc(
                color = PresentColor,
                startAngle = startAngle,
                sweepAngle = presentSweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth)
            )
            startAngle += presentSweep

            // Absent without reason
            drawArc(
                color = AbsentColor,
                startAngle = startAngle,
                sweepAngle = absentSweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth)
            )
            startAngle += absentSweep

            // Absent with reason
            drawArc(
                color = AbsentWithReasonColor,
                startAngle = startAngle,
                sweepAngle = absentWithReasonSweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth)
            )
        }

        Text(
            text = "$percentagePresent%",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
