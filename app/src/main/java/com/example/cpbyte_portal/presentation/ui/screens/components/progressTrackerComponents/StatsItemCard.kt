package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small

@Composable
fun StatsItemCard(
    numberOfQuestions: Int,
    levelOfQuestions: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .width(95.dp)
            .height(95.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(Small),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = numberOfQuestions.toString(),
                color = color,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = levelOfQuestions,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
