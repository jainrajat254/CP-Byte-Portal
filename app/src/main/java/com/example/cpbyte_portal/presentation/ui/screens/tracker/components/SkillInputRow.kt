package com.example.cpbyte_portal.presentation.ui.screens.tracker.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding

@Composable
fun SkillInputRow(
    skillText: String,
    onSkillChange: (String) -> Unit,
    onAddClick: () -> Unit,
    isEnabled: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentHeight()
    ) {
        CPByteTextField(
            value = skillText,
            onValueChange = onSkillChange,
            label = "Enter a new skill",
            hint = "e.g. Kotlin, React, Python",
            modifier = Modifier
                .weight(1f)
                .height(64.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        CPByteButton(
            value = "Add",
            onClick = onAddClick,
            enabled = isEnabled,
            modifier = Modifier
                .padding(top = AppPadding.Small)
                .weight(0.4f)
        )
    }
}

