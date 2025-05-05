package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.time.Duration.Companion.seconds
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.cpbyte_portal.presentation.ui.screens.components.DropDownBox
import com.example.cpbyte_portal.presentation.ui.screens.components.ListItemCard
import com.example.cpbyte_portal.presentation.ui.screens.components.NotificationData
import com.example.cpbyte_portal.presentation.ui.screens.components.TimedMessage
import okhttp3.internal.sse.ServerSentEventReader.Companion.options
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun NotificationScreen() {

    // we have taken variables name as title,message,category,coordinatorRole in mutablestate so that
    // when the user input anything in textfield the data in variables does not reset.
    var title by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }
    var category by remember {
        mutableStateOf("")
    }
    var coordinatorRole by remember {
        mutableStateOf("DSA")
    }

    var showError by remember { mutableStateOf(false) }

    //List for storing notifications
    // I created an data class for notifications so that it is easy to create list elements

    var notifications by remember {
        mutableStateOf(
            mutableListOf(
                NotificationData(
                    "Next DSA Session",
                    coordinatorRole,
                    "DSA",
                    "27 Apr 2025",
                    "08:30 PM",
                    "Join us for the binary trees session..."
                ),
                NotificationData(
                    "Development Workshop",
                    coordinatorRole,
                    "DEV",
                    "26 Apr 2025",
                    "04:00 PM",
                    "Hands-on RESTful API development..."
                ),
                NotificationData(
                    "Development Workshop",
                    "General",
                    "DEV",
                    "26 Apr 2025",
                    "04:00 PM",
                    "Hands-on RESTful API development..."
                )
            )
        )
    }

    // reversing the list so that newer messages will come first.
    notifications.reverse()


    //Calculating current date and time
    val currentDateTime = LocalDateTime.now()

    //formatting current Date and time and store them in seprate variables
    val formattedDate = currentDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    val formattedTime = currentDateTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF17191d))
            .fillMaxHeight()

            // Adding scrollable so that we can easily scroll into the data in the whole screen till end
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Notifications",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1f2129)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(535.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {


                Row(verticalAlignment = Alignment.CenterVertically) {


                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notification Icon",
                        tint = Color(0xFF3569c1)
                    )


                    Spacer(modifier = Modifier.width(8.dp))


                    Text(
                        text = "Send New Notification",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))


                Text(text = "Title", color = Color.White, fontSize = 15.sp)


                Spacer(modifier = Modifier.height(12.dp))


                // Text Field for taking input for sending notifications.
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF17191d),
                        focusedContainerColor = Color(0xFF17191d),
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                    ),
                    placeholder = {
                        Text("Notification title", color = Color.Gray)
                    }

                )


                Spacer(modifier = Modifier.height(12.dp))


                Text(text = "Category", color = Color.White, fontSize = 15.sp)


                Spacer(modifier = Modifier.height(12.dp))

                // Dropdown box for selecting the category of notification.

                DropDownBox(coordinatorRole) {
                    category = it
                }


                Spacer(modifier = Modifier.height(16.dp))


                Text(text = "Message", color = Color.White, fontSize = 15.sp)


                Spacer(modifier = Modifier.height(12.dp))


                TextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF17191d),
                        focusedContainerColor = Color(0xFF17191d),
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                    ),
                    placeholder = {
                        Text("Enter message...", color = Color.Gray)
                    }


                )
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3569c1)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clickable {
                                // If any field is empty the we mark tha flag as true and show the error message
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
                                    // creating a new List so that the list renders again
                                    // and the new notification is added
                                    notifications =
                                        (notifications + newNotification).toMutableList()

                                    //Reseting both the fields after adding the notifications
                                    title = ""
                                    message = ""
                                }

                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // If in the send notification field there is an error or any field is empty then
                        // this message will shown uo for 3 seconds warning the user to fill all fields
                        if (showError) {
                            TimedMessage("Please fill all the fields") {
                                showError = false
                            }

                        }

                        // Send Notification Icon
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send Icon",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Send Notification",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                }
            }
        }
        Text(
            text = "Recent Notifications",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
        )

        // list for each notification
        notifications.forEach { notification ->

            //Displaying each notification and returning a lambda function so that
            //if the user wants to delete the previous notification
            //it can be easily deleted
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
