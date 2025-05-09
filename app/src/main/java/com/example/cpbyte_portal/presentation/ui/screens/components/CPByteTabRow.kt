package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CPByteTabRow(
    tabTitles: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // the row will act as a background container
    Row(
        modifier = modifier
            .background(
                Color(0xFF262632), // background color
                RoundedCornerShape(6.dp)
            )
            .padding(4.dp)  // space inside the background
            .height(36.dp)  // height of the TabRow
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // loop through each tab title and create a tab
        tabTitles.forEachIndexed { index, title ->
            val selected = selectedTab == index
            Box(
                modifier = Modifier
                    .weight(1f) // all tabs take equal width
                    .fillMaxHeight()
                    .background(
                        color = if (selected) Color(0xFF121212)
                        else Color.Transparent,  // tab colored if selected
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { onTabSelected(index) }, //handles tab index
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = if (selected) Color.White  // if selected
                    else Color.LightGray,  // if not selected
                    fontWeight = if (selected) FontWeight.Bold
                    else null,
                    fontSize = 16.sp
                )

            }

        }
    }

}

@Composable
@Preview
fun CPByteTabRowPreview() {
    var selectedTab by rememberSaveable { mutableStateOf(0) }
    val tabTitles = listOf("Upcoming (2)", "Past (1)")

    CPByteTabRow(
        tabTitles = tabTitles,
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }, // update selected tab on click
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

}