package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.ProjectResponse
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium

@Composable
fun ProjectDisplayingCard(
    projectDataClass: ProjectResponse,
) {
    val coverImage = projectDataClass.coverImage
    val projectName = projectDataClass.projectName
    val githubUrl = projectDataClass.githubUrl
    val description = projectDataClass.description
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Medium),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Color(0xFFE4E4E4)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.camer_pfp_selector),
                contentDescription = stringResource(R.string.cover_image),
                tint = Color(0xFFBDBDBD),
                modifier = Modifier.size(48.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 16.dp, 20.dp, 16.dp)
        ) {
            Text(
                text = description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.github),
                        contentDescription = "GitHub Logo",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = projectName,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


