package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
    // Only show the switch for members who are absent (either with or without reason)
    if (member.attendanceStatus == "ABSENT_WITHOUT_REASON" || member.attendanceStatus == "ABSENT_WITH_REASON") {

        // Initialize the toggle based on the member's current attendance status
        var toggleSwitch by rememberSaveable(member.library_id) {
            mutableStateOf(member.attendanceStatus == "ABSENT_WITH_REASON")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
        ) {
            // Switch for toggling "Absent With Reason" status
            Switch(
                checked = toggleSwitch,
                onCheckedChange = {
                    toggleSwitch = it
                    // Update the status when the switch is toggled
                    if (it) {
                        onStatusChange("ABSENT_WITH_REASON")
                    } else {
                        onStatusChange("ABSENT_WITHOUT_REASON")
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF739AE8),  // Color for checked thumb
                    checkedTrackColor = Color.White,        // Color for checked track
                    checkedBorderColor = Color(0xFF739AE8), // Border color when checked
                    uncheckedThumbColor = Color(0xDDFD2C51), // Color for unchecked thumb
                    uncheckedTrackColor = Color.White,      // Color for unchecked track
                    uncheckedBorderColor = Color(0xDDFD2C51) // Border color when unchecked
                ),
                modifier = Modifier
                    .scale(0.7f)
                    .size(30.dp),
            )

            Spacer(modifier = Modifier.width(6.dp))

            // Text describing the switch option
            Text(
                text = "Absent With Reason",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0XFFE0F2FE),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

