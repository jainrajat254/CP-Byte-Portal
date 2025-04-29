package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//@Preview(showBackground = true)
fun UserRoleBox(role: String, rolename: String) {
    Column(
        modifier = Modifier
            .height(70.dp)
            .width(135.dp)
            .background(Color(0xFF000F2D))
            .padding(40.dp, 0.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = role,
            color = Color.Gray,
            fontSize = 11.sp
        )
        Text(
            text = rolename,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

