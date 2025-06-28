package com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Between
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraLarge
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleHeader(
    selectedMonth: Month,
    selectedYear: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    // Header showing current month and year with navigation arrows
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Between, vertical = ExtraLarge),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Title text: "Schedule"
        Text(
            text = stringResource(R.string.schedule),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Inner row for month/year display and arrow controls
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Previous Month Arrow "<"
            Text(
                text = stringResource(R.string.left_arrow),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable {
                        onPreviousClicked()
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))
            // Display the current month and year
            Text(
                text = "${
                    selectedMonth.name.lowercase().replaceFirstChar { it.uppercase() }
                } $selectedYear",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))

            //Next Month Arrow ">"
            Text(
                text = stringResource(R.string.right_arrow),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable {
                        onNextClicked()
                    }
            )
        }
    }
}