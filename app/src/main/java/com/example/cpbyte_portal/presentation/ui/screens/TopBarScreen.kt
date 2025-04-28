package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.R

@Composable
fun TopBar() {
    // Top bar with logo and app name
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.cpbyte_logo),
            contentDescription = "CPBYTE",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "CP",
            color = androidx.compose.ui.graphics.Color.White,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "BYTE",
            color = androidx.compose.ui.graphics.Color(0xFF2EA1F8),
            style = MaterialTheme.typography.titleLarge
        )
    }
}