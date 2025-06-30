package com.example.cpbyte_portal.presentation.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.LightText

@Composable
fun AttendanceCard(
    label: String,
    percentage: Int,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            label, color = LightText, fontSize = 18.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        DoughnutChartWithPercentage(
            percentagePresent = percentage,
            modifier = Modifier.size(140.dp),
            percentageAbsentWithReason = 0,
        )
    }
}