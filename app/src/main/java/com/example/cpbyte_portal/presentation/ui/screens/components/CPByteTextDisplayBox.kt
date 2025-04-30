package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CPByteTextDisplayBox(
    title: String,
    text: String,
    img: ImageVector,
    contentDescription: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(20.dp, 0.dp, 20.dp, 0.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFFFFFFFF),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp)
        )
//        val modifier = if (width.value == 0f) Modifier.fillMaxWidth() else Modifier.width(width)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(0.dp, 4.dp, 0.dp, 0.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1F305A))
                .border(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    width = 1.2.dp
                )
        ) {
            Spacer(modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp))
            Icon(
                imageVector = img,
                modifier = Modifier.size(18.dp),
                contentDescription = contentDescription,
                tint = Color.White
            )
            Text(
                text = text,
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }

    }
}