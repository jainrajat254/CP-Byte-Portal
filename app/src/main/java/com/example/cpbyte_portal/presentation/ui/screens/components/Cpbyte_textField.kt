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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CpbyteText(title: String, text: String, img: ImageVector, contentDescription: String) {
    Column(
        modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)
    ){
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp,0.dp,0.dp,0.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .wrapContentSize()
                .height(20.dp)
                .fillMaxWidth()
                .border(
                    color = Color.White,
                    shape = RoundedCornerShape(5.dp),
                    width = 0.7.dp
                )
                .background(Color(0xff1F305A))
                .padding(0.dp,2.dp,0.dp,0.dp)
        ) {
            Spacer(modifier = Modifier.padding(5.dp,0.dp,0.dp,0.dp))
            Icon(
                imageVector = img,
                modifier = Modifier.size(15.dp),
                contentDescription = contentDescription,
                tint = Color.White
            )
            Text(
                text = text,
                fontSize = 7.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(4.dp,0.dp)
            )
        }

    }
}