package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleHeader(
    selectedMonth: Month,
    selectedYear: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit) {
    // Header showing current month and year with navigation arrows
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Schedule ",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable { onPreviousClicked()
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${
                    selectedMonth.name.lowercase().replaceFirstChar { it.uppercase() }
                } $selectedYear",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00CFFD)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = ">",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable { onNextClicked()
                    }
            )
        }
    }
}
