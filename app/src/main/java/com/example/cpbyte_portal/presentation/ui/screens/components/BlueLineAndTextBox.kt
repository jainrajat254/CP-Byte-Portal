package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineAndTextBox(height : Int,fontsize : Int,detail : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(0.dp, 0.dp, 160.dp, 0.dp)
    ) {
        //card for line to the left of Member Details
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0EC1E7)
            ),
            modifier = Modifier
                .padding(10.dp)
                .width(6.dp)
                .height(height.dp)
        ){}


        Text(
            text = detail,
            fontSize = fontsize.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}