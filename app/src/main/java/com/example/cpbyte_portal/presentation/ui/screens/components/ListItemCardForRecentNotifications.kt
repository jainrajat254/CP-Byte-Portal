package com.example.cpbyte_portal.presentation.ui.screens.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1f2129)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(17.dp)
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
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,

                    )
                IconButton(onClick = {
                    onclick()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Notification Icon",
                        tint = Color.White,
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
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3569c1)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = category, color = Color.White, fontSize = 11.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))

                Text(
                    text = "$date at $time By: $coordinator Coordinator", color = Color.Gray,
                    fontSize = 12.3.sp
                )


            }
            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp))

            Text(
                text = message,
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}



