package com.example.cpbyte_portal.presentation.ui.screens.leaderboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme

@Composable
fun FilterFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }, containerColor = CPByteTheme.brandCyan) {
        Image(
            painter = painterResource(id = R.drawable.baseline_filter_list_alt_24),
            contentDescription = "Filter",
            modifier = Modifier.padding(8.dp)
        )
    }
}