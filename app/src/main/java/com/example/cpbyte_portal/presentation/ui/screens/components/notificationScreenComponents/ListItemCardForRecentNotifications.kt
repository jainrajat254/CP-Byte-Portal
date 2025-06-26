package com.example.cpbyte_portal.presentation.ui.screens.components.notificationScreenComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.CPByteBlue
import com.example.cpbyte_portal.presentation.ui.theme.DarkInputFieldBackground

//Composable for displaying notifications
@Composable
//@Preview
fun ListItemCard(
    title: String,
    category: String,
    coordinator: String,
    date: String,
    time: String,
    message: String,
    onclick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(Medium),
        colors = CardDefaults.cardColors(
            containerColor = DarkInputFieldBackground
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(Medium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,

                    )
                IconButton(onClick = {
                    onclick()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Notification Icon",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .height(20.dp)
                        .weight(1F),
                    colors = CardDefaults.cardColors(containerColor = CPByteBlue),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = category,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 11.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))

                Text(
                    text = "$date at $time By: $coordinator Coordinator",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.3.sp
                )


            }
            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, Medium))

            Text(
                text = message,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp
            )
        }
    }
}



