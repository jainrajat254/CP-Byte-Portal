package com.example.cpbyte_portal.presentation.ui.screens.components

import Member
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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


@Composable
fun ReasonSelectionSwitch(member: Member, onStatusChange: (String) -> Unit) {
    Row() {
        Spacer(Modifier.padding(20.dp))
        var toggleSwitch by rememberSaveable { mutableStateOf(false) }

        //switch only visible on absent
        if (member.status == "ABSENT_WITHOUT_REASON" || member.status == "ABSENT_WITH_REASON") {
            Switch(
                checked = toggleSwitch, onCheckedChange = {
                    toggleSwitch = it
                }, colors = SwitchDefaults.colors(
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
                    .align(Alignment.CenterVertically)
            )
            if (toggleSwitch == true) {
                onStatusChange("ABSENT_WITH_REASON")
            } else
                onStatusChange("ABSENT_WITHOUT_REASON")

            Spacer(Modifier.padding(5.dp))

            Text(
                "Absent With Reason",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0XFFE0F2FE),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}