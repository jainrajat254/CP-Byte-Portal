package com.example.cpbyte_portal.presentation.ui.screens.components

import Member
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarkAllPresentCheckBox(members: SnapshotStateList<Member>) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var isChecked by remember { mutableStateOf(false) }
        if (isChecked == false) {
            Icon(
                imageVector = Icons.Filled.CheckBoxOutlineBlank,
                contentDescription = "unchecked",
                modifier = Modifier
                    .clickable(onClick = {
                        isChecked = true
                        members.forEachIndexed { index, member ->
                            members[index] = members[index].copy(status = "PRESENT")
                        }
                    })
                    .size(20.dp),
            )
            Spacer(modifier = Modifier.width(1.dp))
        } else {
            Icon(
                imageVector = Icons.Filled.CheckBox,
                contentDescription = "unchecked",
                modifier = Modifier
                    .clickable(onClick = {
                        isChecked = false
                        members.forEachIndexed { index, member ->
                            members[index] = members[index].copy(status = "NOT_MARKED")

                        }
                    })
                    .size(20.dp),
            )
        }
        Spacer(modifier = Modifier.width(1.dp))
        Text(
            "Mark All Present",
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier
                .width(120.dp)
                .padding(start = 1.dp, end = 0.5.dp)
        )

    }
}