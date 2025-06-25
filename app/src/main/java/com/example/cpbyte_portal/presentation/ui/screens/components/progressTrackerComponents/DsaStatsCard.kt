package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.LeetCode
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Between

@Composable
fun DsaStatsCard(
    dsaStats: LeetCode,
) {
    val numberOfMediumQuestions = dsaStats.medium
    val numberOfEasyQuestions = dsaStats.easy
    val numberOfHardQuestions = dsaStats.hard

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(Between)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    imageVector = Icons.Outlined.QueryStats,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(27.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.dsa_stats),
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Space between the items
            ) {
                StatsItemCard(
                    numberOfEasyQuestions,
                    stringResource(R.string.easy),
                    Color(0xFF24d3ee)
                )
                StatsItemCard(
                    numberOfMediumQuestions,
                    stringResource(R.string.medium),
                    Color(0xFFfb923c)
                )
                StatsItemCard(
                    numberOfHardQuestions,
                    stringResource(R.string.hard),
                    Color(0xffef4444)
                )
            }
        }
    }
}
