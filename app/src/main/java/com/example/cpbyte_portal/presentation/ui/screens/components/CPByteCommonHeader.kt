package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.textColor

@Composable
fun CommonHeader(
    text: String,
    otherText: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            navigationIcon?.invoke()

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
            )

            Text(
                text = otherText ?: "",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFamily,
                style = TextStyle(brush = textColor)
            )
        }
    }
}
