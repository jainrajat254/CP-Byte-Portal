package com.example.cpbyte_portal.presentation.ui.screens.schedule.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventForm(
    selectedDate: LocalDate,
    eventTitle: String,
    onEventTitleChange: (String) -> Unit,
    eventDescription: String,
    onEventDescriptionChange: (String) -> Unit,
    selectedEventType: String,
    onEventTypeChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    eventTypes: List<String>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppPadding.Medium),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = selectedDate.format(DateTimeFormatter.ofPattern(stringResource(R.string.dd_mmm_yyyy))),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            CPByteTextField(
                value = eventTitle,
                onValueChange = onEventTitleChange,
                hint = "Android Dev",
                label = stringResource(R.string.event_title),
                modifier = Modifier.fillMaxWidth(),
            )

            CPByteTextField(
                value = eventDescription,
                onValueChange = onEventDescriptionChange,
                hint = "5:10 P.M.",
                label = stringResource(R.string.description),
                modifier = Modifier.fillMaxWidth(),
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                modifier = Modifier.fillMaxWidth()
            ) {
                CPByteTextField(
                    value = selectedEventType,
                    onValueChange = onEventTypeChange,
                    label = "Event Type",
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { onExpandedChange(false) }) {
                    eventTypes.forEach { subject ->
                        DropdownMenuItem(text = { Text(subject) }, onClick = {
                            onEventTypeChange(subject)
                            onExpandedChange(false)
                        })
                    }
                }
            }
        }
    }
}
