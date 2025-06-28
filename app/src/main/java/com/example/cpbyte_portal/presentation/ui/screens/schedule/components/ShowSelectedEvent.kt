package com.example.cpbyte_portal.presentation.ui.screens.schedule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraLarge
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small
import com.example.cpbyte_portal.presentation.ui.theme.WarningRed
import java.time.Month

@Composable
fun ShowSelectedEvent(
    selectedDate: Int,
    selectedMonth: Month,
    selectedYear: Int,
    events: Map<Triple<Int, Month, Int>, List<Pair<String, String>>>,
    showEvents: Boolean,
    onDelete: (String) -> Unit,
) {
    val eventKey = Triple(selectedDate, selectedMonth, selectedYear)
    val eventList = events[eventKey]

    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedEventIndex by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = ExtraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showEvents && !eventList.isNullOrEmpty()) {
            eventList.forEachIndexed { index, event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Medium, vertical = Small),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(Medium)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = event.first,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    selectedEventIndex = index
                                    showDeleteDialog = true
                                },
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Event",
                                    tint = WarningRed,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = event.second,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            if (showDeleteDialog && selectedEventIndex >= 0) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = {
                        Text(
                            text = stringResource(R.string.delete_event),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.sure_to_delete_event),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp
                        )
                    },
                    confirmButton = {
                        CPByteButton(
                            value = stringResource(R.string.yes),
                            onClick = {
                                onDelete(eventList[selectedEventIndex].first) // Consider passing an ID instead of title
                                showDeleteDialog = false
                            }
                        )
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = { showDeleteDialog = false },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            border = ButtonDefaults.outlinedButtonBorder
                        ) {
                            Text("No", style = MaterialTheme.typography.labelLarge)
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.surface
                )
            }
        } else {
            Text(
                text = stringResource(R.string.no_events_to_show),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp
            )
        }
    }
}
