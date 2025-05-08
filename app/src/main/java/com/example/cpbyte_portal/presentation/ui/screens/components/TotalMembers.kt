package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.presentation.ui.screens.getDevList
import com.example.cpbyte_portal.presentation.ui.screens.getDsaList

@Composable
fun TotalMembers(selectedTab: Int) {

    //variable for size of members list
    var totalMembers = 0
    if (selectedTab == 0) {
        totalMembers = getDsaList().size
    } else
        totalMembers = getDevList().size


    Text(
        "$totalMembers Members",
        color = Color.White,
        fontWeight = FontWeight.W600,
        modifier = Modifier
            .padding(
                start = 25.dp,
                top = 34.dp,
                bottom = 3.dp
            )
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}