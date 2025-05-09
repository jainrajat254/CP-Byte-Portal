package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccountScreenCard(
    modifier: Modifier,
    content: @Composable () -> Unit,
    colors: CardColors,
    shape: RoundedCornerShape
) {
    Card(
        modifier = modifier,
        colors = colors,
        shape = shape

    ) {
        content()

    }

}