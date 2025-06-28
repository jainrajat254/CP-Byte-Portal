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
import com.example.cpbyte_portal.presentation.ui.theme.AbsentColor
import com.example.cpbyte_portal.presentation.ui.theme.AbsentWithReasonColor
import com.example.cpbyte_portal.presentation.ui.theme.DeepDark
import com.example.cpbyte_portal.presentation.ui.theme.PresentColor
import com.example.cpbyte_portal.presentation.ui.theme.PresentColor1
import com.example.cpbyte_portal.presentation.ui.theme.TrackColor

@Composable
fun DoughnutChartWithPercentage(
    percentagePresent: Int,
    percentageAbsentWithReason: Int,
    modifier: Modifier = Modifier,
    presentColor: Color = PresentColor,
    absentColor: Color = AbsentColor,
    absentWithReasonColor: Color = AbsentWithReasonColor,
    backgroundColor: Color = TrackColor,
    innerCircleRadiusFraction: Float = 0.70f,
) {
    val percentageAbsent = 100 - percentagePresent - percentageAbsentWithReason

    // Convert to angles
    val presentSweep = (percentagePresent / 100f) * 360f
    val absentWithReasonSweep = (percentageAbsentWithReason / 100f) * 360f
    val absentSweep = 360f - presentSweep - absentWithReasonSweep

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .clickable { }) {
            val canvasSize = Size(size.width, size.height)
            var startAngle = -90f

            // Background full circle
            drawArc(
                color = backgroundColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                size = canvasSize
            )

            // Present
            drawArc(
                brush = PresentColor1,
                startAngle = startAngle,
                sweepAngle = presentSweep,
                useCenter = true,
                size = canvasSize
            )
            startAngle += presentSweep

            // Absent
            drawArc(
                color = absentColor,
                startAngle = startAngle,
                sweepAngle = absentSweep,
                useCenter = true,
                size = canvasSize
            )
            startAngle += absentSweep

            // Absent with reason
            drawArc(
                color = absentWithReasonColor,
                startAngle = startAngle,
                sweepAngle = absentWithReasonSweep,
                useCenter = true,
                size = canvasSize
            )

            // Inner circle
            drawCircle(
                color = DeepDark,
                radius = size.width * innerCircleRadiusFraction / 2f,
                center = center,
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
