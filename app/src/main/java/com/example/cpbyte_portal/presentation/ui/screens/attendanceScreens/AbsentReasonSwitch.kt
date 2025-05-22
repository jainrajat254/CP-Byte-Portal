package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.domain.model.DomainUser

@Composable
fun ReasonSelectionSwitch(member: DomainUser, onStatusChange: (String) -> Unit) {
    if (member.attendanceStatus == "ABSENT_WITHOUT_REASON" || member.attendanceStatus == "ABSENT_WITH_REASON") {
        // Initialize the toggle based on the member status
        var toggleSwitch by rememberSaveable(member.library_id) {
            mutableStateOf(member.attendanceStatus == "ABSENT_WITH_REASON")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Switch(
                checked = toggleSwitch,
                onCheckedChange = {
                    toggleSwitch = it
                    if (it) {
                        onStatusChange("ABSENT_WITH_REASON")
                    } else {
                        onStatusChange("ABSENT_WITHOUT_REASON")
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF739AE8),
                    checkedTrackColor = Color.White,
                    checkedBorderColor = Color(0xFF739AE8),
                    uncheckedThumbColor = Color(0xDDFD2C51),
                    uncheckedBorderColor = Color(0xDDFD2C51),
                    uncheckedTrackColor = Color.White,
                ),
                modifier = Modifier
                    .scale(0.7f)
                    .size(25.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                "Absent With Reason",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0XFFE0F2FE)
            )
        }
    }
}
