package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.notificationScreenComponents.DropDownBox
import com.example.cpbyte_portal.presentation.ui.screens.components.notificationScreenComponents.ListItemCard
import com.example.cpbyte_portal.presentation.ui.screens.components.notificationScreenComponents.NotificationData
import com.example.cpbyte_portal.presentation.ui.screens.components.notificationScreenComponents.TimedMessage
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.CPByteBlue
import com.example.cpbyte_portal.presentation.ui.theme.DarkInputFieldBackground
import com.example.cpbyte_portal.presentation.ui.theme.DarkOnSurfaceVariant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun NotificationScreen() {

    // Mutable states for user input fields: title, message, category, and coordinator role.
    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val coordinatorRole by remember { mutableStateOf("DSA") }

    // Flag to handle empty field error indication
    var showError by remember { mutableStateOf(false) }

    // List to hold all the notifications using a data class
    var notifications by remember {
        mutableStateOf(
            mutableListOf(
                NotificationData("Next DSA Session", coordinatorRole, "DSA", "27 Apr 2025", "08:30 PM", "Join us for the binary trees session..."),
                NotificationData("Development Workshop", coordinatorRole, "DEV", "26 Apr 2025", "04:00 PM", "Hands-on RESTful API development..."),
                NotificationData("Development Workshop", "General", "DEV", "26 Apr 2025", "04:00 PM", "Hands-on RESTful API development...")
            )
        )
    }

    // Reversing the list so latest notifications appear first
    notifications.reverse()

    // Getting the current date and time to stamp new notifications
    val currentDateTime = LocalDateTime.now()

    // Formatting current date and time
    val formattedDate = currentDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    val formattedTime = currentDateTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()) // Enables full-screen vertical scrolling
    ) {

        // Heading for the screen
        Text(
            text = "Notifications",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Card for the "Send Notification" form
        Card(
            colors = CardDefaults.cardColors(containerColor = DarkInputFieldBackground),
            modifier = Modifier
                .fillMaxWidth()
                .height(535.dp)
                .padding(Medium)
        ) {
            Column(modifier = Modifier.padding(Medium)) {

                // Row with notification icon and label
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notification Icon",
                        tint = Color(0xFF3569c1)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Send New Notification",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Label for Title field
                Text(text = stringResource(R.string.title), color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(12.dp))

                // TextField for title input
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp //
                    ),
                    placeholder = {
                        Text(stringResource(R.string.notification_title), color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Label for Category field
                Text(text = stringResource(R.string.category), color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(12.dp))

                // Dropdown to select category from coordinator roles
                DropDownBox(coordinatorRole) {
                    category = it
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Label for message field
                Text(text = stringResource(R.string.message), color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(12.dp))

                // TextField for message input
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    placeholder = {
                        Text("Enter message...", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp // ⬅️ Change this to desired size
                    ),
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Send Notification button
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = CardDefaults.cardColors(containerColor = CPByteBlue),
                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clickable {
                                // Validate input fields before sending
                                if (title.isBlank() || message.isBlank() || category.isBlank()) {
                                    showError = true
                                } else {
                                    showError = false
                                    val newNotification = NotificationData(
                                        title,
                                        category,
                                        coordinatorRole,
                                        formattedDate.toString(),
                                        formattedTime.toString(),
                                        message
                                    )
                                    notifications =
                                        (notifications + newNotification).toMutableList()
                                    title = ""      // Reset title
                                    message = ""    // Reset message
                                }
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // Show error message if any input is blank
                        if (showError) {
                            TimedMessage(stringResource(R.string.please_fill_all_the_fields)) {
                                showError = false
                            }
                        }

                        // Icon and label for Send button
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = stringResource(R.string.send_icon),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(R.string.send_notification),
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        // Label for notification history list
        Text(
            text = stringResource(R.string.recent_notifications),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(Medium, 0.dp, 0.dp, 0.dp)
        )

        // Display each notification card with delete functionality
        notifications.forEach { notification ->
            ListItemCard(
                title = notification.title,
                category = notification.category,
                coordinator = notification.coordinator,
                date = notification.date,
                time = notification.time,
                message = notification.message
            ) {
                notifications = notifications.toMutableList().also {
                    it.remove(notification)
                }
            }
        }
    }
}