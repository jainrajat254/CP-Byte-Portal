package com.example.cpbyte_portal.presentation.ui.screens.leaderboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    years: List<String>,
    selectedYear: String,
    onYearSelected: (String) -> Unit,
    showActive: Boolean = false,
    isActive: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {},
    minQuestions: String,
    onMinQuestionsChange: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Filters",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DropdownMenuComponent(
                label = "Year",
                options = years,
                selectedOption = selectedYear,
                onOptionSelected = onYearSelected
            )

            if (showActive) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Status: ${if (isActive) "Active" else "Inactive"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Switch(checked = isActive, onCheckedChange = onActiveChange)
                }
            }

            CPByteTextField(
                value = minQuestions,
                onValueChange = { if (it.all { char -> char.isDigit() }) onMinQuestionsChange(it) },
                label = "Min Questions Solved",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
