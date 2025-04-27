package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun tabSelection(tabs: List<String>, selectedTab: Int,setSelectedTab:(Int)->Unit){
    Row() {
        tabs.forEachIndexed { index, title ->
            Button(
                onClick = { setSelectedTab(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == index)
                        Color(0xff0EC1E7)
                    else Color(0xff313131),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(150.dp, 23.dp)

            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}