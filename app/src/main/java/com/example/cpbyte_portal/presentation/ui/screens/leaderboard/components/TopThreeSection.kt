package com.example.cpbyte_portal.presentation.ui.screens.leaderboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.Leaderboard
import com.example.cpbyte_portal.presentation.ui.theme.RankBronze
import com.example.cpbyte_portal.presentation.ui.theme.RankGold
import com.example.cpbyte_portal.presentation.ui.theme.RankSilver

@Composable
fun TopThreeSection(
    users: List<Leaderboard>,
    navController: NavController,
) {
    val medalColors = listOf(RankGold, RankSilver, RankBronze)
    val imageSizes = listOf(100.dp, 80.dp, 60.dp)
    val columnHeights = listOf(160.dp, 140.dp, 120.dp)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        users.forEachIndexed { index, user ->
            val imageSize = imageSizes.getOrElse(index) { 60.dp }
            val columnHeight = columnHeights.getOrElse(index) { 120.dp }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(100.dp)
                    .height(columnHeight)
            ) {
                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .border(3.dp, medalColors[index], CircleShape)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    if (!user.avatar.isNullOrBlank()) {
                        AsyncImage(
                            model = user.avatar,
                            contentDescription = "${user.name}'s avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.baseline_person_24),
                            contentDescription = "Default avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = when (index) {
                        0 -> "1st"
                        1 -> "2nd"
                        2 -> "3rd"
                        else -> ""
                    },
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = medalColors[index],
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "@${user.leetcodeUsername}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
