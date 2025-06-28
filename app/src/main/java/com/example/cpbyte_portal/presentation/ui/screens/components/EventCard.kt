package com.example.cpbyte_portal.presentation.ui.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
import com.example.cpbyte_portal.presentation.ui.theme.SuccessGreen
import com.example.cpbyte_portal.presentation.ui.theme.WarningRed
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraExtraSmall
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventCard(
    event: EventData,
    onEdit: () -> Unit, // Called when edit is clicked
    onDelete: () -> Unit  // Called when delete is clicked

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Small, vertical = ExtraExtraSmall), // Space around the card
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
        shape = RoundedCornerShape(8.dp)  // rounded corners
    ) {
        Column(
            modifier = Modifier
                .padding(Medium)  // Space inside the card
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //Event title
                Text(
                    text = event.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                //Category label with color coding
                Box(
                    modifier = Modifier
                        .background(
                            color = when (event.category.lowercase()) {
                                "general" -> SuccessGreen.copy(alpha = 0.2f)
                                else -> Color.Gray.copy(alpha = 0.2f)
                            },
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = event.category,
                        color = when (event.category.lowercase()) {
                            "general" -> SuccessGreen
                            else -> Color.Gray
                        },
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            //Event description
            Text(
                text = event.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
            )
            Spacer(Modifier.height(8.dp))

            //Row for event date and calender icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Icon(
                    Icons.Default.CalendarToday,
                    contentDescription = stringResource(R.string.date),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        event.date.format(DateTimeFormatter.ISO_LOCAL_DATE)  // date format: YYYY-MM-DD
                    } else {
                        event.date.toString()  //Fallback for APIs lower than 26 - code to be written
                    },
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .weight(.5f)
                )

            }
            Spacer(Modifier.height(10.dp))

            //Row for edit and delete buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),

                horizontalArrangement = Arrangement.End
            ) {
                // Edit button with glowing effect
                GlowingIconButton(
                    onClick = onEdit,
                    icon = {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = stringResource(R.string.edit),
                            tint = Color.White
                        )
                    },
                    baseColor = MaterialTheme.colorScheme.background,
                    glowColor = CPByteTheme.accentCyan
                )

                Spacer(Modifier.size(12.dp))
                //Delete button with glowing effect
                GlowingIconButton(
                    onClick = onDelete,
                    icon = {
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = stringResource(R.string.delete),
                            tint = WarningRed
                        )
                    },
                    baseColor = MaterialTheme.colorScheme.background,
                    glowColor = CPByteTheme.accentCyan                )

            }
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun EventCardPreview() {
    val sampleEvent = EventData(
        title = "DSA Workshop",
        description = "Deep dive into binary trees, traversals, and common problems.",
        category = "General",
        date = LocalDate.of(2025, 5, 15),
    )
    EventCard(
        event = sampleEvent,
        onEdit = {},
        onDelete = {}
    )

}